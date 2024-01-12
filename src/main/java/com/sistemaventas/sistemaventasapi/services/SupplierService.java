package com.sistemaventas.sistemaventasapi.services;

import com.sistemaventas.sistemaventasapi.dto.SupplierGetDTO;
import com.sistemaventas.sistemaventasapi.dto.SupplierPostDTO;
import com.sistemaventas.sistemaventasapi.dto.SupplierUpdateDTO;
import com.sistemaventas.sistemaventasapi.entities.SupplierEntity;
import com.sistemaventas.sistemaventasapi.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private EntityManager em;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<SupplierGetDTO> findAllSupliers(){
        List<SupplierEntity> suppliersList = supplierRepository.findAll();
        List<SupplierGetDTO> supplierGetDTOList = suppliersList.stream()
                .map(m-> modelMapper.map(m, SupplierGetDTO.class)).collect(Collectors.toList());
        return supplierGetDTOList;
    }

    public Page<SupplierGetDTO> findSupliers(int page, int size){
        Page<SupplierEntity> suppliersList = supplierRepository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by(new Sort.Order(Sort.Direction.ASC, "name"))
                )
        );
        List<SupplierGetDTO> supplierGetDTOList = suppliersList.stream()
                .map(m-> modelMapper.map(m, SupplierGetDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(supplierGetDTOList, suppliersList.getPageable(), suppliersList.getTotalPages());
    }

    public Page<SupplierGetDTO> findSuppliersByFilters(String supplierName, int page, int size) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<SupplierEntity> root = countQuery.from(SupplierEntity.class);

        Predicate[] predicatesArray = buildPredicates(criteriaBuilder, root, supplierName);

        // Aplicar los predicados a la countQuery
        countQuery.where(predicatesArray);
        countQuery.select(criteriaBuilder.count(root));

        Long total = em.createQuery(countQuery).getSingleResult();

        // Consulta principal con paginación
        CriteriaQuery<SupplierEntity> criteriaQuery = criteriaBuilder.createQuery(SupplierEntity.class);
        criteriaQuery.from(SupplierEntity.class);
        criteriaQuery.where(predicatesArray);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("name")));

        Pageable pageable = PageRequest.of(page, size);
        List<SupplierEntity> filteredList = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        List<SupplierGetDTO> response = filteredList.stream()
                .map(s -> modelMapper.map(s, SupplierGetDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(response, pageable, total);
    }

    private Predicate[] buildPredicates(CriteriaBuilder criteriaBuilder, Root<SupplierEntity> root, String supplierName) {
        List<Predicate> predicates = new ArrayList<>();

        if (supplierName != null) {
            Expression<String> upperCaseName = criteriaBuilder.upper(root.get("name"));
            Predicate supplierNameFilter = criteriaBuilder.like(upperCaseName, supplierName.toUpperCase() + "%");
            predicates.add(supplierNameFilter);
        }

        return predicates.toArray(new Predicate[0]);
    }

    public SupplierGetDTO findSupplierById(UUID id){
        SupplierEntity supplier = supplierRepository.findById(id).get();
        return modelMapper.map(supplier, SupplierGetDTO.class);
    }

    public SupplierGetDTO createSupplier(SupplierPostDTO supplier){
        SupplierEntity supplierToCreate = new SupplierEntity();
        supplierToCreate.setName(supplier.getName());
        supplierToCreate.setDocumentType(supplier.getDocumentType());
        supplierToCreate.setDocument(supplier.getDocument());
        supplierToCreate.setDetails(supplier.getDetails());
        supplierToCreate.setAddress(supplier.getAddress());
        //supplierToCreate.setUserCreate(supplier.getUserCreate());
        supplierToCreate.setDateCreate(LocalDateTime.now());

        SupplierEntity response = supplierRepository.save(supplierToCreate);
        return modelMapper.map(response, SupplierGetDTO.class);
    }

    public SupplierGetDTO updateSupplier(SupplierUpdateDTO supplier){
        SupplierEntity supplierToUpdate = supplierRepository.findById(supplier.getId()).get();
        supplierToUpdate.setName(supplier.getName());
        supplierToUpdate.setDocumentType(supplier.getDocumentType());
        supplierToUpdate.setDocument(supplier.getDocument());
        supplierToUpdate.setDetails(supplier.getDetails());
        supplierToUpdate.setAddress(supplier.getAddress());
        //supplierToCreate.setUserCreate(supplier.getUserCreate());
        supplierToUpdate.setDateCreate(LocalDateTime.now());

        SupplierEntity response = supplierRepository.save(supplierToUpdate);
        return modelMapper.map(response, SupplierGetDTO.class);
    }

    public void deleteSupplier(String id){
            supplierRepository.deleteById(UUID.fromString(id));
    }

}
