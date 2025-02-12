package com.mansour.inventoryservice.service;

import com.mansour.inventoryservice.dto.InventoryRequestDto;
import com.mansour.inventoryservice.dto.InventoryResponseDto;
import com.mansour.inventoryservice.entity.Inventory;

import java.util.List;

public class InventoryService implements BaseService<InventoryRequestDto, InventoryResponseDto, Long> {
    @Override
    public InventoryResponseDto create(InventoryRequestDto inventoryRequestDto) {
        return null;
    }

    @Override
    public InventoryResponseDto get(Long aLong) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public InventoryResponseDto update(InventoryRequestDto inventoryRequestDto, Long aLong) {
        return null;
    }

    @Override
    public List<InventoryResponseDto> getAll() {
        return List.of();
    }
}
