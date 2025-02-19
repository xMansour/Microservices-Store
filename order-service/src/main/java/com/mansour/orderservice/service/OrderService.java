package com.mansour.orderservice.service;

import com.mansour.orderservice.dto.ApiResponse;
import com.mansour.orderservice.dto.inventory.InventoryResponseDto;
import com.mansour.orderservice.dto.order.OrderRequestDto;
import com.mansour.orderservice.dto.order.OrderResponseDto;
import com.mansour.orderservice.entity.Order;
import com.mansour.orderservice.entity.OrderItem;
import com.mansour.orderservice.exception.HttpStatusMessageKey;
import com.mansour.orderservice.exception.InsufficientInventoryException;
import com.mansour.orderservice.exception.OrderNotFoundException;
import com.mansour.orderservice.mapper.order.OrderEntityToResponseDtoMapper;
import com.mansour.orderservice.mapper.order.OrderRequestDtoToEntityMapper;
import com.mansour.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService implements BaseService<OrderRequestDto, OrderResponseDto, Long> {
    private final OrderRepository orderRepository;
    private final OrderRequestDtoToEntityMapper orderRequestDtoToEntityMapper;
    private final OrderEntityToResponseDtoMapper orderEntityToResponseDtoMapper;
    private final WebClient webClient;

    @Override
    public OrderResponseDto create(OrderRequestDto orderRequestDto) {
        log.info("OrderService#create orderRequestDto={}", orderRequestDto);
        Order order = orderRepository.save(orderRequestDtoToEntityMapper.apply(orderRequestDto));
        List<String> orderItemsCodes = order.getOrderItems().stream().map(OrderItem::getCode).toList();

        ParameterizedTypeReference<ApiResponse<List<InventoryResponseDto>>> responseType =
                new ParameterizedTypeReference<>() {
                };
        ApiResponse<List<InventoryResponseDto>> response = webClient.get()
                .uri("http://localhost:8082/api/v1/inventories", uriBuilder -> uriBuilder.queryParam("codes", orderItemsCodes.toArray()).build())
                .retrieve()
                .bodyToMono(responseType)
                .block();
        boolean isInStock = response != null
                && response.isSuccess()
                && response.getData().size() == order.getOrderItems().size()
                && response.getData().stream().allMatch(InventoryResponseDto::getIsInStock);
        if (!isInStock)
            throw new InsufficientInventoryException(HttpStatusMessageKey.INSUFFICIENT_INVENTORY);
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
