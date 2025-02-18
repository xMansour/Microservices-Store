package com.mansour.inventoryservice.mapper;

import com.mansour.inventoryservice.dto.inventory.InventoryResponseDto;
import com.mansour.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InventoryEntityToResponseDtoMapper implements Function<Inventory, InventoryResponseDto> {
    @Override
    public InventoryResponseDto apply(Inventory inventory) {
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .code(inventory.getCode())
                .quantity(inventory.getQuantity())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
