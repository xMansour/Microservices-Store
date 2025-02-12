package com.mansour.orderservice.service;

import com.mansour.orderservice.dto.order.OrderRequestDto;
import com.mansour.orderservice.dto.order.OrderResponseDto;
import com.mansour.orderservice.entity.Order;
import com.mansour.orderservice.exception.HttpStatusMessageKey;
import com.mansour.orderservice.exception.OrderNotFoundException;
import com.mansour.orderservice.mapper.order.OrderEntityToResponseDtoMapper;
import com.mansour.orderservice.mapper.order.OrderRequestDtoToEntityMapper;
import com.mansour.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService implements BaseService<OrderRequestDto, OrderResponseDto, Long> {
    private final OrderRepository orderRepository;
    private final OrderRequestDtoToEntityMapper orderRequestDtoToEntityMapper;
    private final OrderEntityToResponseDtoMapper orderEntityToResponseDtoMapper;

    @Override
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        log.info("OrderService#create orderRequestDto={}", orderRequestDto);
        Order order = orderRepository.save(orderRequestDtoToEntityMapper.apply(orderRequestDto));
        return orderEntityToResponseDtoMapper.apply(order);
    }

    @Override
    public OrderResponseDto get(Long id) {
        log.info("OrderService#get id={}", id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(HttpStatusMessageKey.ORDER_NOT_FOUND));
        return orderEntityToResponseDtoMapper.apply(order);
    }

    @Override
    public void delete(Long id) {
        log.info("OrderService#delete id={}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public OrderResponseDto update(OrderRequestDto orderRequestDto, Long id) {
        log.info("OrderService#update orderRequestDto={}, id={}", orderRequestDto, id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(HttpStatusMessageKey.ORDER_NOT_FOUND));
        order = orderRequestDtoToEntityMapper.updateEntity(order, orderRequestDto);
        order = orderRepository.save(order);
        return orderEntityToResponseDtoMapper.apply(order);
    }

    @Override
    public List<OrderResponseDto> getAll() {
        log.info("OrderService#getAll");
        List<OrderResponseDto> orderResponseDtos = orderRepository.findAll().stream().map(orderEntityToResponseDtoMapper).toList();
        return orderResponseDtos;
    }
}
