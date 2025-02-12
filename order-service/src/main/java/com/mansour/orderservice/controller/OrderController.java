package com.mansour.orderservice.controller;

import com.mansour.orderservice.dto.ApiResponse;
import com.mansour.orderservice.dto.order.OrderRequestDto;
import com.mansour.orderservice.dto.order.OrderResponseDto;
import com.mansour.orderservice.service.OrderService;
import com.mansour.orderservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> placeOrder(@RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        OrderResponseDto orderResponseDto = orderService.create(orderRequestDto);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(orderResponseDto,
                                "Order with id:%s placed successfully".formatted(orderResponseDto.getId()),
                                request.getRequestURI())
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAllOrders(HttpServletRequest request) {
        List<OrderResponseDto> orderResponseDtos = orderService.getAll();
        return ResponseEntity
                .ok(ResponseUtil
                        .success(orderResponseDtos,
                                "Orders fetched successfully",
                                request.getRequestURI())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrder(@PathVariable Long id, HttpServletRequest request) {
        OrderResponseDto orderResponseDto = orderService.get(id);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(orderResponseDto,
                                "Order with id:%s fetched successfully".formatted(orderResponseDto.getId()),
                                request.getRequestURI())
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDto orderRequestDto, HttpServletRequest request) {
        OrderResponseDto orderResponseDto = orderService.update(orderRequestDto, id);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(orderResponseDto,
                                "Order with id:%s updated successfully".formatted(orderResponseDto.getId()),
                                request.getRequestURI())
                );
    }
}
