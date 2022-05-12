package com.ra.model.repository_impl;

import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.enums.OrderSort;
import com.ra.model.enums.OrderState;
import com.ra.model.repository.OrderRepository;
import com.ra.model.repository.OrderRepositoryCustom;
import com.ra.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> getOrders(String searchString, OrderState orderState, OrderSort orderSort, Boolean invertedSort){
        String sql = "SELECT * FROM orders";
        if (orderState != null) sql += " WHERE state = " + orderState.toInt();
        sql += " ORDER BY " + (orderSort == OrderSort.BY_COST?"cost":"creationTime");
        sql += (invertedSort != null && invertedSort)?" DESC":" ASC";

        List<Order> orders = entityManager.createNativeQuery(sql, Order.class).getResultList();

        if (searchString != null && !searchString.isEmpty()) {
            orders.removeIf(order -> order.getMaster() == null);
            orders.removeIf(order -> !order.getMaster().getEmail().toLowerCase().contains(searchString.trim().toLowerCase()));
        }

        return orders;
    }
}
