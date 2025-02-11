package com.mansour.productservice.mapper.product;

import com.mansour.productservice.dto.ProductResponseDto;
import com.mansour.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductEntityToResponseDtoMapper implements Function<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto apply(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
