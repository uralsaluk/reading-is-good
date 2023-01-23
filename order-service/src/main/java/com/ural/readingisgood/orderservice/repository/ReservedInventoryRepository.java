package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.ReservedInventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@EnableMongoRepositories
@Repository
@Transactional
public interface ReservedInventoryRepository extends MongoRepository<ReservedInventory, String> {


}
