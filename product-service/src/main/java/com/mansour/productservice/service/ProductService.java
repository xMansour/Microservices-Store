package com.mansour.productservice.service;

import com.mansour.productservice.dto.ApiResponse;
import com.mansour.productservice.dto.ProductRequestDto;
import com.mansour.productservice.dto.ProductResponseDto;
import com.mansour.productservice.entity.Product;
import com.mansour.productservice.exception.HttpStatusMessageKey;
import com.mansour.productservice.exception.ProductNotFoundException;
import com.mansour.productservice.mapper.product.ProductEntityToResponseDtoMapper;
import com.mansour.productservice.mapper.product.ProductRequestDtoToEntityMapper;
import com.mansour.productservice.repository.ProductRepository;
import com.mansour.productservice.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements BaseService<ProductRequestDto, ProductResponseDto, String> {
    private final ProductRepository productRepository;
    private final ProductRequestDtoToEntityMapper productRequestDtoToEntityMapper;
    private final ProductEntityToResponseDtoMapper productEntityToResponseDtoMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        log.info("ProductService#create productRequestDto={}", productRequestDto);
        Product product = productRepository.save(productRequestDtoToEntityMapper.apply(productRequestDto));
        return productEntityToResponseDtoMapper.apply(product);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        log.info("ProductService#getAll");
        List<ProductResponseDto> productResponseDtos = productRepository.findAll().stream().map(productEntityToResponseDtoMapper).toList();
        return productResponseDtos;
    }

    @Override
    public ProductResponseDto get(String id) {
        log.info("ProductService#get id={}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(HttpStatusMessageKey.PRODUCT_NOT_FOUND));
        return productEntityToResponseDtoMapper.apply(product);
    }

    @Override
    public void delete(String id) {
        log.info("ProductService#delete id={}", id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, String id) {
        log.info("ProductService#update productRequestDto={}", productRequestDto);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(HttpStatusMessageKey.PRODUCT_NOT_FOUND));
        product = productRequestDtoToEntityMapper.updateEntity(product, productRequestDto);
        productRepository.save(product);
        return productEntityToResponseDtoMapper.apply(product);
    }


}
