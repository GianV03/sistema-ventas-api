package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.ProductGetDTO;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    private ModelMapper modelMapper;

    public ResponseEntity<List<ProductGetDTO>> getAllProducts(){
        List<ProductEntity> products = productRepository.findAll();
        List<ProductGetDTO> response = products.stream().
                                       map(x -> modelMapper.map(x, ProductGetDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }
}
