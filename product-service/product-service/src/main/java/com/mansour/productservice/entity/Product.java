package com.mansour.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String id;
    @Field(name = "name")
    @Indexed(unique = true)
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "price")
    private Double price;
    @Field(name = "quantity")
    private Long quantity;
}
