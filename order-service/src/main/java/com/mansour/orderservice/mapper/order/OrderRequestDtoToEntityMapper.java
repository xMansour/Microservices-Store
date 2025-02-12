package com.mansour.orderservice.mapper.order;

import com.mansour.orderservice.dto.order.OrderRequestDto;
import com.mansour.orderservice.entity.Order;
import com.mansour.orderservice.mapper.oderitems.OrderItemRequestDtoToEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OrderRequestDtoToEntityMapper implements Function<OrderRequestDto, Order> {
    private final OrderItemRequestDtoToEntityMapper orderItemRequestDtoToEntityMapper;

    @Override
    public Order apply(OrderRequestDto orderRequestDto) {
        return Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderItems(orderRequestDto.getOrderItems().stream().map(orderItemRequestDtoToEntityMapper).toList())
                .build();
    }


    public Order updateEntity(Order order, OrderRequestDto orderRequestDto) {
        order.setOrderItems(orderRequestDto.getOrderItems().stream().map(orderItemRequestDtoToEntityMapper).toList());
        return order;
    }
}
