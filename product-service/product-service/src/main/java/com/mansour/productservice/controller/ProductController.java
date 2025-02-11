package com.mansour.productservice.controller;

import com.mansour.productservice.dto.ApiResponse;
import com.mansour.productservice.dto.ProductRequestDto;
import com.mansour.productservice.dto.ProductResponseDto;
import com.mansour.productservice.service.ProductService;
import com.mansour.productservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(@RequestBody ProductRequestDto product, HttpServletRequest request) {
        ProductResponseDto productResponseDto = productService.create(product);
        return ResponseEntity
                .ok(ResponseUtil
                        .success(productResponseDto,
                                "Product:%s created successfully".formatted(productResponseDto.getName()),
                                request.getRequestURI())
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts(HttpServletRequest request) {
        List<ProductResponseDto> productResponseDtos = productService.getAll();
        return ResponseEntity
                .ok(ResponseUtil
                        .success(productResponseDtos,
                                "Products fetched successfully",
                                request.getRequestURI())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable String id, HttpServletRequest request) {
        ProductResponseDto productResponseDto = productService.get(id);
        return ResponseEntity.ok(ResponseUtil
                .success(productResponseDto,
                        "Product:%s fetched  successfully".formatted(productResponseDto.getName()),
                        request.getRequestURI())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@PathVariable String id, @RequestBody ProductRequestDto productRequestDto, HttpServletRequest request) {
        ProductResponseDto productResponseDto = productService.update(productRequestDto, id);
        return ResponseEntity.ok(ResponseUtil
                .success(productResponseDto,
                        "Product:%s updated successfully".formatted(productResponseDto.getName()),
                        request.getRequestURI())
        );
    }


}
