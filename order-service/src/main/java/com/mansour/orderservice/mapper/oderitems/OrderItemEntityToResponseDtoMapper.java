package com.mansour.orderservice.mapper.oderitems;

import com.mansour.orderservice.dto.orderitem.OrderItemResponseDto;
import com.mansour.orderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderItemEntityToResponseDtoMapper implements Function<OrderItem, OrderItemResponseDto> {
    @Override
    public OrderItemResponseDto apply(OrderItem orderItem) {
        return OrderItemResponseDto.builder()
                .id(orderItem.getId())
                .code(orderItem.getCode())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
