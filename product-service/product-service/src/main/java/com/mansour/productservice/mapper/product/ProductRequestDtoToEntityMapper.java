package com.mansour.productservice.mapper.product;

import com.mansour.productservice.dto.ProductRequestDto;
import com.mansour.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductRequestDtoToEntityMapper implements Function<ProductRequestDto, Product> {
    @Override
    public Product apply(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public Product updateEntity(Product product, ProductRequestDto productRequestDto) {
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        return product;
    }
}
