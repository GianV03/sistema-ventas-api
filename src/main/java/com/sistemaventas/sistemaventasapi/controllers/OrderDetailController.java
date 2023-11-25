package com.sistemaventas.sistemaventasapi.controllers;

import com.sistemaventas.sistemaventasapi.dto.OrderDetailGetDTO;
import com.sistemaventas.sistemaventasapi.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<List<OrderDetailGetDTO>> findOrderDetailsByOrderId(
            @PathVariable String id
    ){
        try{
            List<OrderDetailGetDTO> orderDetailGetDTOList = orderDetailService.findOrderDetailsByOrderId(UUID.fromString(id));
            return ResponseEntity.ok(orderDetailGetDTOList);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
