package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.ProductGetDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductPostDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductUpdatePostDTO;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductTypeEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import com.sistemaventas.sistemaventasapi.entities.UserEntity;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductTypeRepository;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import com.sistemaventas.sistemaventasapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProductGetDTO> getAllProducts(int page, int size){
        Page<ProductEntity> products = productRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(new Sort.Order(Sort.Direction.ASC, "name"))
        ));
        List<ProductGetDTO> response = products.stream().
                                       map(x -> modelMapper.map(x, ProductGetDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(response, products.getPageable(), products.getTotalPages());
    }

    public ProductGetDTO findById(UUID id){
        ProductEntity product = productRepository.findById(id).get();
        ProductGetDTO response = modelMapper.map(product, ProductGetDTO.class);
        return response;
    }

    public ProductGetDTO createProduct(ProductPostDTO product){
        ProductEntity productToCreate = new ProductEntity();
        productToCreate.setName(product.getName());
        ProductTypeEntity type = productTypeRepository.findById(product.getTypeId()).get();
        productToCreate.setType(type);
        productToCreate.setSalePrice(product.getSalePrice());
        productToCreate.setPurchasePrice(product.getPurchasePrice());
        SupplierEntity supplier = supplierRepository.findById(product.getSupplierId()).get();
        productToCreate.setSupplier(supplier);
        productToCreate.setDetails(product.getDetails());
        //UserEntity user = userRepository.findById(product.getUserCreationId()).get();
        //productToCreate.setUserCreation(user);
        productToCreate.setCreationDate(product.getCreationDate());

        ProductEntity response = productRepository.save(productToCreate);
        return modelMapper.map(response, ProductGetDTO.class);
    }

    public ProductGetDTO updateProduct(ProductUpdatePostDTO product){
        ProductEntity productToUpdate = productRepository.findById(product.getId()).get();
        productToUpdate.setName(product.getName());
        ProductTypeEntity type = productTypeRepository.findById(product.getTypeId()).get();
        productToUpdate.setType(type);
        productToUpdate.setSalePrice(product.getSalePrice());
        productToUpdate.setPurchasePrice(product.getPurchasePrice());
        SupplierEntity supplier = supplierRepository.findById(product.getSupplierId()).get();
        productToUpdate.setSupplier(supplier);
        productToUpdate.setDetails(product.getDetails());
        //UserEntity user = userRepository.findById(product.getUserCreationId()).get();
        //productToCreate.setUserCreation(user);
        productToUpdate.setCreationDate(product.getCreationDate());

        ProductEntity response = productRepository.save(productToUpdate);
        return modelMapper.map(response, ProductGetDTO.class);
    }

}
