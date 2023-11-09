package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.ProductTypeGetDTO;
import com.sistemaventas.sistemaventasapi.entities.ProductEntity;
import com.sistemaventas.sistemaventasapi.entities.ProductTypeEntity;
import com.sistemaventas.sistemaventasapi.repositories.ProductTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductTypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    public List<ProductTypeGetDTO> findAllProductType(){
        List<ProductTypeEntity> productTypes = productTypeRepository.findAll();
        return productTypes.stream().map(pt-> modelMapper.map(pt, ProductTypeGetDTO.class))
                .collect(Collectors.toList());
    }
}
