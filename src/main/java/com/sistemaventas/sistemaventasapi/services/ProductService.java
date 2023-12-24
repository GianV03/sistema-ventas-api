package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.ProductGetDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductPostDTO;
import com.sistemaventas.sistemaventasapi.dto.ProductUpdatePostDTO;
import com.sistemaventas.sistemaventasapi.entities.*;
import com.sistemaventas.sistemaventasapi.repositories.ProductRepository;
import com.sistemaventas.sistemaventasapi.repositories.ProductTypeRepository;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import com.sistemaventas.sistemaventasapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private EntityManager em;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProductGetDTO> getAllProducts(int page, int size){
        Page<ProductEntity> products = productRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(new Sort.Order(Sort.Direction.ASC, "name"))
        ));
        List<ProductGetDTO> response = products.stream().
                                       map(x -> modelMapper.map(x, ProductGetDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(response, products.getPageable(), products.getTotalPages());
    }

    // Búsqueda de productos por filtros
    public Page<ProductGetDTO> findProductsByFilters(String name, String productType, Integer page, Integer size){

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> root = criteriaQuery.from(ProductEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if(name!=null){
            Expression<String> upperCaseName = criteriaBuilder.upper(root.get("name"));
            Predicate productNameFilter = criteriaBuilder.like(upperCaseName, "%" + name.toUpperCase() + "%");
            predicates.add(productNameFilter);
        }

        if (productType != null && !productType.equals("")) {
            ProductTypeEntity type = productTypeRepository.findById(UUID.fromString(productType)).orElse(null);
            if (type != null) {
                Predicate productTypeFilter = criteriaBuilder.equal(root.get("type"), type);
                predicates.add(productTypeFilter);
            }
        }

        Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
        var filters = criteriaBuilder.and(predicatesArray);
        criteriaQuery.where(filters);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("name")));

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(ProductEntity.class)));
        //countQuery.where(filters);

        Long total = em.createQuery(countQuery).getSingleResult();

        Pageable pageable = PageRequest.of(
                page,
                     size
        );

        List<ProductEntity> filteredProducts = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        List<ProductGetDTO> response = filteredProducts.stream()
                .map(p -> modelMapper.map(p, ProductGetDTO.class)).collect(Collectors.toList());

        return new PageImpl<>(response, pageable, total);
    }

    public Page<ProductGetDTO> findProductsByName( String name, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<ProductEntity> productsList = productRepository.findByNameStartingWithIgnoreCase(name);
        List<ProductGetDTO> productsPage = productsList
                .stream()
                .map(product -> modelMapper.map(product, ProductGetDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(productsPage, pageable, productsList.size());
    }

    public ProductGetDTO findById(UUID id){
        ProductEntity product = productRepository.findById(id).get();
        ProductGetDTO response = modelMapper.map(product, ProductGetDTO.class);
        return response;
    }

    public ProductGetDTO createProduct(ProductPostDTO product){
        ProductEntity productToCreate = new ProductEntity();
        productToCreate.setName(product.getName());
        ProductTypeEntity type = productTypeRepository.findById(product.getTypeId()).get();
        productToCreate.setType(type);
        productToCreate.setSalePrice(product.getSalePrice());
        productToCreate.setPurchasePrice(product.getPurchasePrice());
        SupplierEntity supplier = supplierRepository.findById(product.getSupplierId()).get();
        productToCreate.setSupplier(supplier);
        productToCreate.setDetails(product.getDetails());
        //UserEntity user = userRepository.findById(product.getUserCreationId()).get();
        //productToCreate.setUserCreation(user);
        productToCreate.setCreationDate(product.getCreationDate());

        ProductEntity response = productRepository.save(productToCreate);
        return modelMapper.map(response, ProductGetDTO.class);
    }

    public ProductGetDTO updateProduct(ProductUpdatePostDTO product){
        ProductEntity productToUpdate = productRepository.findById(product.getId()).get();
        productToUpdate.setName(product.getName());
        ProductTypeEntity type = productTypeRepository.findById(product.getTypeId()).get();
        productToUpdate.setType(type);
        productToUpdate.setSalePrice(product.getSalePrice());
        productToUpdate.setPurchasePrice(product.getPurchasePrice());
        SupplierEntity supplier = supplierRepository.findById(product.getSupplierId()).get();
        productToUpdate.setSupplier(supplier);
        productToUpdate.setDetails(product.getDetails());
        //UserEntity user = userRepository.findById(product.getUserCreationId()).get();
        //productToCreate.setUserCreation(user);
        productToUpdate.setCreationDate(product.getCreationDate());

        ProductEntity response = productRepository.save(productToUpdate);
        return modelMapper.map(response, ProductGetDTO.class);
    }

}
