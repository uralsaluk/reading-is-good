package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.OrderHeaderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderHeaderEntity, Long> {


    List<OrderHeaderEntity> findByCreateDateBetween(Date startDate, Date endDate);

    Page<OrderHeaderEntity> findAllByUserName(String username, Pageable pageable);


}