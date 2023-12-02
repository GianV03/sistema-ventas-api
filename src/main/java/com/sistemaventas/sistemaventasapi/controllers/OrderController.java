package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.OrderGetDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderPostDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderTrayDTO;
import com.sistemaventas.sistemaventasapi.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderTrayDTO>> findAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        try{
            Page<OrderTrayDTO> ordersPage = orderService.findAllOrders(page, size);
            return ResponseEntity.ok(ordersPage);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderGetDTO> findOrderById(
            @PathVariable String id
    ){
        try{
            OrderGetDTO order = orderService.findOrderById(UUID.fromString(id));
            return ResponseEntity.ok(order);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping
    public ResponseEntity<URI> createOrder(
            @RequestBody OrderPostDTO order,
            UriComponentsBuilder ucb
            ){
        try{
            OrderGetDTO createdOrder = orderService.createOrder(order);
            URI locationOfNewOrder = ucb
                    .path("/oders/{id}")
                    .buildAndExpand(createdOrder.getId())
                    .toUri();
            return ResponseEntity.ok(locationOfNewOrder);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
