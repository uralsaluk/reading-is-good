package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Component
public class InventoryRepositoryImpl {


    @Autowired
    private EntityManager entityManager;


    public InventoryEntity getById(Long id) {

        InventoryEntity entity = entityManager.find(InventoryEntity.class, id, LockModeType.PESSIMISTIC_READ);


        return entity;
    }
}
