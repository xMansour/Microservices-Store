package com.mansour.orderservice.dto.order;

import com.mansour.orderservice.dto.orderitem.OrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private List<OrderItemResponseDto> orderItems;
}
