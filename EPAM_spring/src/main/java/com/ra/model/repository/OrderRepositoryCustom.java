package com.ra.model.repository;

import com.ra.model.entity.Order;
import com.ra.model.enums.OrderSort;
import com.ra.model.enums.OrderState;

import java.util.List;

public interface OrderRepositoryCustom {
    //List<Order> getOrdersByMasterEmail(String masterEmail);
    //List<Order> getOrdersByUserEmail(String userEmail);
    List<Order> getOrders(String searchString, OrderState orderState, OrderSort orderSort, Boolean invertedSort);
}
