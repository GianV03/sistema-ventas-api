package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.SupplierGetDTO;
import com.sistemaventas.sistemaventasapi.dto.SupplierPostDTO;
import com.sistemaventas.sistemaventasapi.dto.SupplierUpdateDTO;
import com.sistemaventas.sistemaventasapi.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @GetMapping
    public ResponseEntity<List<SupplierGetDTO>> findAllSuppliers(){
        try{
            List<SupplierGetDTO> supplierGetDTOList = supplierService.findAllSupliers();
            return ResponseEntity.ok(supplierGetDTOList);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SupplierGetDTO>> findAllSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<SupplierGetDTO> supplierGetDTOList = supplierService.findSupliers(page, size);
            return ResponseEntity.ok(supplierGetDTOList);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/filters")
    public ResponseEntity<Page<SupplierGetDTO>> findSuppliersByFilters(
            @RequestParam(required = false) String supplierName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            return ResponseEntity.ok(supplierService.findSuppliersByFilters( supplierName, page, size));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierGetDTO> findSupplierById(
            @PathVariable String id
    ){
        try{
            SupplierGetDTO supplier = supplierService.findSupplierById(UUID.fromString(id));
            return ResponseEntity.ok(supplier);
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<URI> createSupplier(
            @RequestBody SupplierPostDTO supplierToCreate,
            UriComponentsBuilder ucb
            ){
        try{
            SupplierGetDTO supplierCreated = supplierService.createSupplier(supplierToCreate);
            URI locationOfNewSupplier =
                    ucb.path("/supplier/{id}")
                            .buildAndExpand(supplierCreated.getId())
                            .toUri();
            return ResponseEntity.created(locationOfNewSupplier).build();
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<URI> updateSupplier(
            @RequestBody SupplierUpdateDTO supplierToUpdate,
            UriComponentsBuilder ucb
    ){
        try{
            SupplierGetDTO supplierUpdated = supplierService.updateSupplier(supplierToUpdate);
            URI locationOfNewSupplier =
                    ucb.path("/supplier/{id}")
                            .buildAndExpand(supplierUpdated.getId())
                            .toUri();
            return ResponseEntity.ok(locationOfNewSupplier);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(
            @PathVariable String id
    ){
        try{
            supplierService.deleteSupplier(id);
            return ResponseEntity.ok("Se ha eliminado el proveedor");
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
