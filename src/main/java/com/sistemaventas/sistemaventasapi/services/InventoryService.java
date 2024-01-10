package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.InventoryGetDTO;
import com.sistemaventas.sistemaventasapi.dto.InventoryPostDTO;
import com.sistemaventas.sistemaventasapi.dto.InventoryUpdateDTO;
import com.sistemaventas.sistemaventasapi.entities.InventoryEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductTypeEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import com.sistemaventas.sistemaventasapi.repositories.InventoryRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductTypeRepository;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private EntityManager em;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;
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

        return new PageImpl<>(inventoryList, inventoryPage.getPageable(), inventoryPage.getTotalElements());
    }

    public InventoryGetDTO findInventoryById(UUID id){

        InventoryEntity ivnentory = inventoryRepository.findById(id).get();
        return modelMapper.map(inventoryRepository, InventoryGetDTO.class);
    }
    public Page<InventoryGetDTO> findInventoryByFilters(String productName, String productType, int page, int size){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> criteriaQuery = criteriaBuilder.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = criteriaQuery.from(InventoryEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if(productName!=null){
            Expression<String> upperCaseProductName = criteriaBuilder.upper(root.get("product").get("name"));
            Predicate productFilter = criteriaBuilder.like(upperCaseProductName, "%" + productName.toUpperCase() + "%");
            predicates.add(productFilter);
        }

        if(productType!= null){
            ProductTypeEntity type = productTypeRepository.findById(UUID.fromString(productType)).orElse(null);
            Predicate productTypeFilter = criteriaBuilder.equal(root.get("product").get("type"), type);
            predicates.add(productTypeFilter);
        }

        Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
        var filters = criteriaBuilder.and(predicatesArray);
        criteriaQuery.where(filters);

        int total = criteriaQuery.getOrderList().size();

        Pageable pageable = PageRequest.of(
                page,
                size
        );

        List<InventoryEntity> filteredInventory = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        List<InventoryGetDTO> response = filteredInventory.stream()
                .map(p -> modelMapper.map(p, InventoryGetDTO.class)).collect(Collectors.toList());

        return new PageImpl<>(response, pageable, total);
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
