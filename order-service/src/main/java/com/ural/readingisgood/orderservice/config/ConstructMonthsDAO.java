package com.ural.readingisgood.orderservice.config;

import com.ural.readingisgood.orderservice.entity.MonthEntity;
import com.ural.readingisgood.orderservice.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ConstructMonthsDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StatisticsRepository statisticsRepository;

    private static final DateFormatSymbols dfs = new DateFormatSymbols();

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void buildAndFeedMonthsConstantTable() {


        String[] months = dfs.getMonths();
        //	entityManager.getTransaction().begin();

        List<String> monthList = Arrays.asList(months).stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());


        for (int i = 0; i < monthList.size(); i++) {

            MonthEntity monthEntity = entityManager.find(MonthEntity.class, Long.valueOf(i + 1));

            if (Objects.isNull(monthEntity)) {

                entityManager.persist(new MonthEntity(Long.valueOf(i + 1), monthList.get(i)));
            }


        }

        //	entityManager.getTransaction().commit();


    }


}
