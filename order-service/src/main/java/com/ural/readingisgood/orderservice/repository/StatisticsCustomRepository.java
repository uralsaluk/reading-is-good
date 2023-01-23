package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.MonthlyStatisticsDAOModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StatisticsCustomRepository {

    private final static String getMonthlyStatisticsByUserIdQuery = " SELECT M.NAME," +
            " COALESCE(DATA.COUNTOFORDERS,?1) COUNTOFORDERS , " +
            " COALESCE(DATA.TOTALPRICE,?2) TOTALPRICE ," +
            "  COALESCE(DATA.TOTALQUANTITY, ?3) TOTALQUANTITY  \n" +
            "from  months_constant M \n" +
            "  LEFT JOIN \n" +
            "(\n" +
            "SELECT   \n" +
            " MONTH(H.CREATE_DATE)  AS MONTH,\n" +
            "\n" +
            " COUNT(H.ID) COUNTOFORDERS , SUM(H.TOTAL_PRICE) TOTALPRICE, SUM(QUANTITY)  TOTALQUANTITY " +
            "FROM      order_headers H  LEFT JOIN       (SELECT ORDER_ID , SUM(QUANTITY) QUANTITY FROM  order_lines L GROUP BY ORDER_ID)   L ON   H.ID =L.ORDER_ID   \n" +
            "\n" +
            " WHERE   H.USER_NAME= ?4 AND YEAR(H.CREATE_DATE)= ?5 \n" +
            "GROUP BY  MONTH(H.CREATE_DATE)  ) AS DATA     \n" +
            "                  ON M.ID=DATA .MONTH";


    @Autowired
    private EntityManager entityManager;


    public List<MonthlyStatisticsDAOModel> getMonthlyStatisticsByUserId(String userId, Integer year) {

        Query query = entityManager.createNativeQuery(
                getMonthlyStatisticsByUserIdQuery);
        List<Object> params = Arrays.asList(0, 0, 0, userId, year);
        query.setParameter(1, 0);
        query.setParameter(2, 0);
        query.setParameter(3, 0);
        query.setParameter(4, userId);
        query.setParameter(5, year);
        List<Object[]> statistics = query.getResultList();

        List<MonthlyStatisticsDAOModel> statisticsDAOList = new ArrayList<>();

        for (Object[] statistic : statistics) {

            String name = (String) statistic[0];
            BigInteger countOfOrders = (BigInteger) statistic[1];
            Double totalPrice = (Double) statistic[2];
            BigDecimal totalQuantity = (BigDecimal) statistic[3];

            statisticsDAOList.add(new MonthlyStatisticsDAOModel(name, countOfOrders.intValue(), totalPrice, totalQuantity.intValue()));

        }


        return statisticsDAOList;

    }
}
