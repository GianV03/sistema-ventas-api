package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.InventoryGetDTO;
import com.sistemaventas.sistemaventasapi.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<Page<InventoryGetDTO>> findAllInventory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        try{

            Page<InventoryGetDTO> inventoryPage = inventoryService.findInventory(page, size);
            return ResponseEntity.ok(inventoryPage);

        }catch(Exception exception){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

}
