package com.mansour.inventoryservice.service;

import com.mansour.inventoryservice.dto.inventory.InventoryRequestDto;
import com.mansour.inventoryservice.dto.inventory.InventoryResponseDto;
import com.mansour.inventoryservice.entity.Inventory;
import com.mansour.inventoryservice.exception.HttpStatusMessageKey;
import com.mansour.inventoryservice.exception.InventoryNotFoundException;
import com.mansour.inventoryservice.mapper.InventoryEntityToResponseDtoMapper;
import com.mansour.inventoryservice.mapper.InventoryRequestDtoToEntityMapper;
import com.mansour.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService implements BaseService<InventoryRequestDto, InventoryResponseDto, Long> {
    private final InventoryRepository inventoryRepository;
    private final InventoryRequestDtoToEntityMapper inventoryRequestDtoToEntityMapper;
    private final InventoryEntityToResponseDtoMapper inventoryEntityToResponseDtoMapper;

    @Override
    public InventoryResponseDto create(InventoryRequestDto inventoryRequestDto) {
        log.info("InventoryService#create inventoryRequestDto={}", inventoryRequestDto);
        Inventory inventory = inventoryRepository.save(inventoryRequestDtoToEntityMapper.apply(inventoryRequestDto));
        return inventoryEntityToResponseDtoMapper.apply(inventory);
    }

    @Override
    public InventoryResponseDto get(Long id) {
        log.info("InventoryService#get id={}", id);
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(HttpStatusMessageKey.INVENTORY_NOT_FOUND));
        return inventoryEntityToResponseDtoMapper.apply(inventory);
    }

    @Override
    public void delete(Long id) {
        log.info("InventoryService#delete id={}", id);
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(HttpStatusMessageKey.INVENTORY_NOT_FOUND));
        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryResponseDto update(InventoryRequestDto inventoryRequestDto, Long id) {
        log.info("InventoryService#update inventoryRequestDto={}, id={}", inventoryRequestDto, id);
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(HttpStatusMessageKey.INVENTORY_NOT_FOUND));
        inventory = inventoryRequestDtoToEntityMapper.updateEntity(inventory, inventoryRequestDto);
        inventory = inventoryRepository.save(inventory);
        return inventoryEntityToResponseDtoMapper.apply(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAll() {
        log.info("InventoryService#getAll");
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(inventoryEntityToResponseDtoMapper).toList();
    }

    public boolean isInStock(String code) {
        log.info("InventoryService#isInStock code={}", code);
        Inventory inventory = inventoryRepository.findByCode(code).orElseThrow(() -> new InventoryNotFoundException(HttpStatusMessageKey.INVENTORY_NOT_FOUND));
        return inventory.getQuantity() > 0;
    }

    public List<InventoryResponseDto> isInStock(List<String> codes) {
        log.info("InventoryService#isInStock codes={}", codes);
        List<Inventory> inventories = inventoryRepository.findByCodeIn(codes);
        return inventories.stream().map(inventoryEntityToResponseDtoMapper).toList();
    }
}
