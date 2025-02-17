package com.mansour.inventoryservice.mapper;

import com.mansour.inventoryservice.dto.inventory.InventoryRequestDto;
import com.mansour.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InventoryRequestDtoToEntityMapper implements Function<InventoryRequestDto, Inventory> {
    @Override
    public Inventory apply(InventoryRequestDto inventoryRequestDto) {
        return Inventory.builder()
                .code(inventoryRequestDto.getCode())
                .quantity(inventoryRequestDto.getQuantity())
                .build();
    }

    public Inventory updateEntity(Inventory inventory, InventoryRequestDto inventoryRequestDto) {
        inventory.setCode(inventoryRequestDto.getCode());
        inventory.setQuantity(inventoryRequestDto.getQuantity());
        return inventory;
    }
}
