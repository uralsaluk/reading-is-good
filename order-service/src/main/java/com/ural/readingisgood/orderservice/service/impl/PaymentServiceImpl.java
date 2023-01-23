package com.ural.readingisgood.orderservice.service.impl;

import com.ural.readingisgood.orderservice.event.PaymentFailedEvent;
import com.ural.readingisgood.orderservice.exception.BusinessException;
import com.ural.readingisgood.orderservice.exception.ErrorCode;
import com.ural.readingisgood.orderservice.service.contract.PaymentService;
import com.ural.readingisgood.orderservice.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ApplicationEventPublisher publisher;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public String exchange(String reservedInventoryId) {

        Random randomNum = new Random();
        int number = randomNum.nextInt(100);

        if (number == 55) {


            LOGGER.info("Payment failed for reservedInventoryID: {}", reservedInventoryId);
            publisher.publishEvent(new PaymentFailedEvent(ContextUtil.getContextStrategy(),
                    reservedInventoryId
            ));

            throw new BusinessException(ErrorCode.PAYMENT_ERROR);
        }

        LOGGER.info("Payment successful for reservedInventoryID: {}", reservedInventoryId);
        return UUID.randomUUID().toString();
    }
}
