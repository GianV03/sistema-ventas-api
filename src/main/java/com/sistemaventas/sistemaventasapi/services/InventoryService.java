package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.InventoryGetDTO;
import com.sistemaventas.sistemaventasapi.dto.InventoryPostDTO;
import com.sistemaventas.sistemaventasapi.dto.InventoryUpdateDTO;
import com.sistemaventas.sistemaventasapi.entities.InventoryEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import com.sistemaventas.sistemaventasapi.repositories.InventoryRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<InventoryGetDTO> findInventory(int page, int size){
        Page<InventoryEntity> inventoryPage = inventoryRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(new Sort.Order(Sort.Direction.ASC, "stock"))
                )
        );
        List<InventoryGetDTO> inventoryList = inventoryPage.stream()
                .map(inventory-> modelMapper.map(inventory, InventoryGetDTO.class)).collect(Collectors.toList());

        return new PageImpl<>(inventoryList, inventoryPage.getPageable(), inventoryPage.getTotalPages());
    }

    public InventoryGetDTO findInventoryById(UUID id){

        InventoryEntity ivnentory = inventoryRepository.findById(id).get();
        return modelMapper.map(inventoryRepository, InventoryGetDTO.class);
    }

    public InventoryGetDTO saveIntentory(InventoryPostDTO inventory){

        InventoryEntity inventoryToSave = new InventoryEntity();

        ProductEntity product = productRepository.findById(inventory.getProduct().getId()).get();
        inventoryToSave.setProduct(product);
        SupplierEntity supplier = supplierRepository.findById(inventory.getSupplier().getId()).get();
        inventoryToSave.setSupplier(supplier);

        InventoryEntity response = inventoryRepository.save(inventoryToSave);

        return modelMapper.map(response, InventoryGetDTO.class);
    }

    public InventoryGetDTO updateIntentory(InventoryUpdateDTO inventory){

        InventoryEntity inventoryToUpdate = inventoryRepository.findById(inventory.getId()).get();

        ProductEntity product = productRepository.findById(inventory.getProduct().getId()).get();
        inventoryToUpdate.setProduct(product);
        SupplierEntity supplier = supplierRepository.findById(inventory.getSupplier().getId()).get();
        inventoryToUpdate.setSupplier(supplier);

        InventoryEntity response = inventoryRepository.save(inventoryToUpdate);

        return modelMapper.map(response, InventoryGetDTO.class);
    }

}
