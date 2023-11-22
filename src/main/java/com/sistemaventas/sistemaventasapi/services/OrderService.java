package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.OrderGetDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderPostDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderTrayDTO;
import com.sistemaventas.sistemaventasapi.entities.OrderEntity;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import com.sistemaventas.sistemaventasapi.repositories.OrderRepository;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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

    public OrderGetDTO findOrderById(UUID id){
        OrderEntity order = orderRepository.findById(id).get();
        return modelMapper.map(order, OrderGetDTO.class);
    }

    public OrderGetDTO createOrder(OrderPostDTO order){
        OrderEntity orderToCreate = new OrderEntity();
        SupplierEntity supplier = supplierRepository.findById(order.getSupplier()).get();
        orderToCreate.setSupplier(supplier);
        orderToCreate.setOrderDate(order.getOrderDate());
        orderToCreate.setOrderDeliveryDate(order.getOrderDeliveryDate());
        orderToCreate.setOrderRealDeliveryDate(order.getOrderRealDeliveryDate());
        orderToCreate.setState(order.getState());
        orderToCreate.setSubTotal(order.getSubTotal());
        orderToCreate.setTotal(order.getTotal());

        OrderEntity response = orderRepository.save(orderToCreate);

        return modelMapper.map(response, OrderGetDTO.class);
    }

}
