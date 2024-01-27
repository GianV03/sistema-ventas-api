package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.ProductGetDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductPostDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductUpdatePostDTO;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PostUpdate;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductGetDTO>> findAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue ="5") int size
    ){
        try {
            return ResponseEntity.ok(productService.getAllProducts(page, size));
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/filters")
    public ResponseEntity<Page<ProductGetDTO>> findProductByFilters(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try {
            return ResponseEntity.ok(productService.findProductsByFilters(productName,productType, page, size));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/byName")
    public ResponseEntity<Page<ProductGetDTO>> findProductsByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        try{
            return ResponseEntity.ok(productService.findProductsByName(name, page, size));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/bySupplier/{supplierId}")
    public ResponseEntity<List<ProductGetDTO>> findProductsBySupplier(
            @PathVariable String supplierId
    ){
        try{
            return ResponseEntity.ok().body(productService.findProductsBySupplier(supplierId));
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDTO> findProductById(
            @PathVariable String id
    ){
        try{
            return ResponseEntity.ok(productService.findById(UUID.fromString(id)));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<URI> createProduct(
            @RequestBody ProductPostDTO product,
            UriComponentsBuilder ucb
            ){
        try{
            ProductGetDTO savedProduct = productService.createProduct(product);
            URI locationOfNewProduct = ucb
                    .path("products/{id}")
                    .buildAndExpand(savedProduct.getId())
                    .toUri();
            return ResponseEntity.created(locationOfNewProduct).build();

        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<?> editProduct(
            @RequestBody ProductUpdatePostDTO product,
            UriComponentsBuilder ucb
            ){
        try{
            ProductGetDTO productUpdated = productService.updateProduct(product);
            URI locationOfProduct = ucb
                    .path("products/{id}")
                    .buildAndExpand(productUpdated.getId())
                    .toUri();
            return ResponseEntity.ok(locationOfProduct);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable String id
    ){
        try{
            boolean deleted = productService.deleteProduct(UUID.fromString(id));
            if(deleted){
                return ResponseEntity.ok().body("Se eliminó el registro");
            }else{
                return ResponseEntity.ok().body("No existe el registro");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
