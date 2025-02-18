package com.mansour.inventoryservice.controller;

import com.mansour.inventoryservice.dto.ApiResponse;
import com.mansour.inventoryservice.dto.inventory.InventoryResponseDto;
import com.mansour.inventoryservice.service.InventoryService;
import com.mansour.inventoryservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryResponseDto>>> isInStock(@RequestParam List<String> codes, HttpServletRequest request) {
        List<InventoryResponseDto> isInStock = inventoryService.isInStock(codes);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(isInStock,
                                "Inventories checked successfully",
                                request.getRequestURI())
                );
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> healthCheck(HttpServletRequest request) {
        return ResponseEntity
                .ok(ResponseUtil
                        .success("Inventory service is up and running",
                                "Inventory service is up and running",
                                request.getRequestURI())
                );
    }


}
