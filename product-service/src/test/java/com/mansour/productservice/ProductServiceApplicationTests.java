package com.mansour.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mansour.productservice.dto.ProductRequestDto;
import com.mansour.productservice.repository.ProductRepository;
import com.mongodb.assertions.Assertions;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateAProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ProductRequestDto.builder()
                                .name("Product 1")
                                .description("Product 1 description")
                                .price(100.0)
                                .quantity(10L)
                                .build())))
                .andExpect(status().isOk());

        Assertions.assertTrue(!productRepository.findAll().isEmpty());
    }
}
