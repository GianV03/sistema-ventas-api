package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.OrderDetailGetDTO;
import com.sistemaventas.sistemaventasapi.entities.OrderDetailEntity;
import com.sistemaventas.sistemaventasapi.repositories.OrderDetailRepository;
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
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetailGetDTO> findOrderDetailsByOrderId(UUID orderId){
        List<OrderDetailEntity> orderDetailsList = orderDetailRepository.findByOrder_Id(orderId);
        List<OrderDetailGetDTO> response = orderDetailsList.stream()
                .map(od-> modelMapper.map(od, OrderDetailGetDTO.class))
                .collect(Collectors.toList());
        return response;
    }

}
