package com.ra.model.repository;

import com.ra.model.entity.Manager;
import com.ra.model.entity.Order;
import com.ra.model.enums.OrderSort;
import com.ra.model.enums.OrderState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer>, OrderRepositoryCustom {
    @Query(value = "SELECT * FROM orders WHERE masterEmail = ?1 ORDER BY creationTime", nativeQuery = true)
    List<Order> getOrdersByMasterEmail(String masterEmail);

    @Query(value = "SELECT * FROM orders WHERE userEmail = ?1 ORDER BY creationTime", nativeQuery = true)
    List<Order> getOrdersByUserEmail(String userEmail);
}
