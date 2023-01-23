package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    InventoryEntity getOne(Long aLong);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<InventoryEntity> findWithLockingById(Long aLong);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<List<InventoryEntity>> findWithLockingByIdIn(Collection<Long> ids);


}
