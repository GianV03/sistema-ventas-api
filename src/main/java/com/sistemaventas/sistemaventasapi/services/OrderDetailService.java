package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.OrderDetailGetDTO;
import com.sistemaventas.sistemaventasapi.dto.OrderDetailPostDTO;
import com.sistemaventas.sistemaventasapi.entities.OrderDetailEntity;
import com.sistemaventas.sistemaventasapi.entities.OrderEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.repositories.OrderDetailRepository;
import com.sistemaventas.sistemaventasapi.repositories.OrderRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderDetailService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetailGetDTO> findOrderDetailsByOrderId(UUID orderId){
        List<OrderDetailEntity> orderDetailsList = orderDetailRepository.findByOrder_Id(orderId);
        List<OrderDetailGetDTO> response = orderDetailsList.stream()
                .map(od-> modelMapper.map(od, OrderDetailGetDTO.class))
                .collect(Collectors.toList());
        return response;
    }

    public String saveOrderDetails(OrderDetailPostDTO[] orderDetails){

        for (OrderDetailPostDTO orderDetail:
             orderDetails) {

            OrderDetailEntity od = new OrderDetailEntity();
            OrderEntity order = orderRepository.findById(orderDetail.getOrderId()).get();
            od.setOrder(order);
            ProductEntity product = productRepository.findById(orderDetail.getProductId()).get();
            od.setProduct(product);
            od.setPrice(orderDetail.getPrice());
            od.setQuantity(orderDetail.getQuantity());
            od.setTotal(orderDetail.getTotal());

            OrderDetailEntity response = orderDetailRepository.save(od);
        }
        return "Se guardaron los detalles";
    }

}
