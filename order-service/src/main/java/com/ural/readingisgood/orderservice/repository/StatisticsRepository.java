package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.MonthlyStatisticsDAOModel;
import com.ural.readingisgood.orderservice.entity.OrderHeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<OrderHeaderEntity, Long> {


    String getMonthlyStatisticsByUserIdQuery = "select  M.NAME, DATA.COUNTOFORDERS,DATA.TOTALPRICE,DATA.TOTALQUANTITY\n" +
            "from  MONTHS_CONSTANT M\n" +
            "  LEFT JOIN \n" +
            "(\n" +
            "SELECT   \n" +
            " MONTH(H.CREATE_DATE)  AS MONTH,\n" +
            "\n" +
            " COUNT(H.*) COUNTOFORDERS , SUM(H.TOTAL_PRICE) TOTALPRICE, SUM(QUANTITY)  TOTALQUANTITY\n" +
            "FROM      ORDER_HEADERS H  LEFT JOIN       (SELECT ORDER_ID , SUM(QUANTITY) QUANTITY FROM  ORDER_LINES L GROUP BY ORDER_ID)   L ON   H.ID =L.ORDER_ID   \n" +
            "\n" +
            " WHERE   H.USER_NAME= ?1 AND YEAR(H.CREATE_DATE)= ?2 \n" +
            "GROUP BY  MONTH(H.CREATE_DATE)  ) AS DATA     \n" +
            "                  ON M.ID=DATA .MONTH";


    @Query(value = getMonthlyStatisticsByUserIdQuery, nativeQuery = true)
    List<MonthlyStatisticsDAOModel> getMonthlyStatisticsByUserId(String userId, Integer year);
}