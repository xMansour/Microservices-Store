package com.mansour.orderservice.mapper.oderitems;

import com.mansour.orderservice.dto.orderitem.OrderItemRequestDto;
import com.mansour.orderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderItemRequestDtoToEntityMapper implements Function<OrderItemRequestDto, OrderItem> {
    @Override
    public OrderItem apply(OrderItemRequestDto orderItemRequestDto) {
        return OrderItem.builder()
                .code(orderItemRequestDto.getCode())
                .price(orderItemRequestDto.getPrice())
                .quantity(orderItemRequestDto.getQuantity())
                .build();
    }

    public OrderItem updateEntity(OrderItem orderItem, OrderItemRequestDto orderItemRequestDto) {
        orderItem.setCode(orderItemRequestDto.getCode());
        orderItem.setPrice(orderItemRequestDto.getPrice());
        orderItem.setQuantity(orderItemRequestDto.getQuantity());
        return orderItem;
    }
}
