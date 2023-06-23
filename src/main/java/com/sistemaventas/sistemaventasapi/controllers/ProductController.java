package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.ProductGetDTO;
import com.sistemaventas.sistemaventasapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductGetDTO>> findAllProducts(){
        try {
            return productService.getAllProducts();
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
