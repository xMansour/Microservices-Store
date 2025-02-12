package com.mansour.inventoryservice.mapper;

import com.mansour.inventoryservice.dto.InventoryResponseDto;
import com.mansour.inventoryservice.entity.Inventory;

import java.util.function.Function;

public class InventoryEntityToResponseDtoMapper implements Function<Inventory, InventoryResponseDto> {
    @Override
    public InventoryResponseDto apply(Inventory inventory) {
        return InventoryResponseDto.builder()
                .id(inventory.getId())
                .code(inventory.getCode())
                .quantity(inventory.getQuantity())
                .build();
    }
}
