package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.OrderGetDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderTrayDTO;
import com.sistemaventas.sistemaventasapi.entities.OrderEntity;
import com.sistemaventas.sistemaventasapi.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

     @Autowired
     private ModelMapper modelMapper;

    public Page<OrderTrayDTO> findAllOrders(int page, int size){

        Page<OrderEntity> ordersPage = orderRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(new Sort.Order(Sort.Direction.ASC, "orderDate"))
                )
        );

        List<OrderTrayDTO> ordersList = ordersPage.stream()
                .map(x-> modelMapper.map(x, OrderTrayDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(ordersList, ordersPage.getPageable(), ordersPage.getTotalPages());
    }



}
