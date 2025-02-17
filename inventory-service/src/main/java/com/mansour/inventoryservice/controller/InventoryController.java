package com.mansour.inventoryservice.controller;

import com.mansour.inventoryservice.dto.ApiResponse;
import com.mansour.inventoryservice.service.InventoryService;
import com.mansour.inventoryservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<Boolean>> isInStock(@PathVariable String code, HttpServletRequest request) {
        boolean isInStock = inventoryService.isInStock(code);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(isInStock,
                                "Inventory with code:%s checked successfully".formatted(code),
                                request.getRequestURI())
                );

    }
}
