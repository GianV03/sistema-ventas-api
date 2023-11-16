package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.OrderGetDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderTrayDTO;
import com.sistemaventas.sistemaventasapi.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
