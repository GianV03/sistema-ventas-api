package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.ProductTypeGetDTO;
import com.sistemaventas.sistemaventasapi.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product-types")
public class ProductTypeController {

    @Autowired
    public ProductTypeService productTypeService;
    @GetMapping
    public ResponseEntity<List<ProductTypeGetDTO>> findAllProductType(){
        try{
            List<ProductTypeGetDTO> productTypes = productTypeService.findAllProductType();
            return ResponseEntity.ok(productTypes);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
