package com.ural.readingisgood.orderservice.service.impl;

import com.ural.readingisgood.orderservice.entity.InventoryEntity;
import com.ural.readingisgood.orderservice.entity.ReservedInventory;
import com.ural.readingisgood.orderservice.entity.ReservedProduct;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.exception.ErrorCode;
import com.ural.readingisgood.orderservice.repository.BookRepository;
import com.ural.readingisgood.orderservice.repository.InventoryRepository;
import com.ural.readingisgood.orderservice.repository.ReservedInventoryRepository;
import com.ural.readingisgood.orderservice.service.contract.InventoryService;
import com.ural.readingisgood.orderservice.service.model.orderservice.request.OrderedProductDTO;
import com.ural.readingisgood.orderservice.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private BookRepository bookRepository;
    private InventoryRepository inventoryRepository;

    private ReservedInventoryRepository reservedInventoryRepository;

    @Autowired
    public InventoryServiceImpl(BookRepository bookRepository, InventoryRepository inventoryRepository, ReservedInventoryRepository reservedInventoryRepository) {
        this.bookRepository = bookRepository;
        this.inventoryRepository = inventoryRepository;
        this.reservedInventoryRepository = reservedInventoryRepository;
    }

    @Transactional
    @Override
    public ReservedInventory reserveInventory(Set<OrderedProductDTO> orderedProductList) {

        Optional<List<InventoryEntity>> optionalInventoryEntityList =
                inventoryRepository
                        .findWithLockingByIdIn(orderedProductList.stream()
                                .map(x -> x.getProductId()).collect(Collectors.toList()));


        if (optionalInventoryEntityList.isPresent()
                && checkInventories(orderedProductList, optionalInventoryEntityList.get())) {

            List<InventoryEntity> inventoryEntities = optionalInventoryEntityList.get();

            ReservedInventory reservedInventory = new ReservedInventory();
            List<ReservedProduct> reservedProductList = new ArrayList<>();

            for (InventoryEntity inventoryEntity : inventoryEntities) {

                OrderedProductDTO orderedProductDTO = orderedProductList
                        .stream().filter(x -> x.getProductId().equals(inventoryEntity.getId())).findFirst().get();

                inventoryEntity.setStock(inventoryEntity.getStock() - orderedProductDTO.getQuantity());
                reservedProductList.add(new ReservedProduct(inventoryEntity.getId(), orderedProductDTO.getQuantity()));

                inventoryRepository.save(inventoryEntity);


            }
            reservedInventory.setId(UUID.randomUUID().toString());
            reservedInventory.setProductList(reservedProductList);

            ReservedInventory savedReservedInventory = reservedInventoryRepository.save(reservedInventory);

            LOGGER.info("{} reserved for user {}, with {} reserved ID ",
                    reservedInventory, ContextUtil.getUserId(), reservedInventory.getId());

            return savedReservedInventory;


        } else {

            throw new BusinessException(ErrorCode.NOT_ENOUGH_STOCK);
        }


    }

    @Override
    public void confirmReservedInventory(String reservedInventoryId) {
        ReservedInventory reservedInventory = reservedInventoryRepository.findById(reservedInventoryId).get();


        reservedInventory.setConfirmed(true);
        reservedInventoryRepository.save(reservedInventory);

        LOGGER.info(" Reserved ID: {} confirmed ",
                reservedInventory.getId());
    }

    @Override
    @Transactional
    public void rollBackInventories(String reservedInventoryId) {

        ReservedInventory reservedInventory = reservedInventoryRepository.findById(reservedInventoryId).get();


        for (ReservedProduct reservedProduct : reservedInventory.getProductList()) {

            increaseInventory(reservedProduct);
        }

        reservedInventory.setConfirmed(true);
        reservedInventoryRepository.save(reservedInventory);

        LOGGER.info(" Rollback applied for  Reserved ID: {}  ",
                reservedInventory.getId());


    }

    @Transactional
    public void increaseInventory(ReservedProduct rollbackProduct) {


        InventoryEntity inventoryEntity = inventoryRepository.findWithLockingById(rollbackProduct.getBookId()).get();
        inventoryEntity.setStock(inventoryEntity.getStock() + rollbackProduct.getQuantity());
        inventoryRepository.save(inventoryEntity);

    }

    public Boolean checkInventories(Set<OrderedProductDTO> orderedProductList, List<InventoryEntity> inventoryEntities) {


        if (orderedProductList.size() == inventoryEntities.size()) {


            for (OrderedProductDTO productDTO : orderedProductList) {

                Optional<InventoryEntity> optionalInventoryEntity = inventoryEntities.stream().filter(x -> x.getId() == productDTO.getProductId()).findFirst();

                if (!checkQuantity(productDTO, optionalInventoryEntity.get())) {
                    return false;
                }


            }
            return true;
        }

        return false;
    }


    private boolean checkQuantity(OrderedProductDTO request, InventoryEntity inventoryEntity) {

        if (inventoryEntity != null &
                inventoryEntity.getStock() != 0 && (inventoryEntity.getStock() - request.getQuantity() >= 0)) {

            return true;
        }

        return false;
    }


}
