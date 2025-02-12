package com.mansour.orderservice.mapper.order;

import com.mansour.orderservice.dto.order.OrderResponseDto;
import com.mansour.orderservice.entity.Order;
import com.mansour.orderservice.mapper.oderitems.OrderItemEntityToResponseDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OrderEntityToResponseDtoMapper implements Function<Order, OrderResponseDto> {
    private final OrderItemEntityToResponseDtoMapper orderItemEntityToResponseDtoMapper;

    @Override
    public OrderResponseDto apply(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderItems(order.getOrderItems().stream().map(orderItemEntityToResponseDtoMapper).toList())
                .build();
    }
}
