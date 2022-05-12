package com.ra.model.service;

import com.ra.model.entity.Master;
import com.ra.model.entity.Order;
import com.ra.model.repository.ManagerRepository;
import com.ra.model.repository.MasterRepository;
import com.ra.model.repository.OrderRepository;
import com.ra.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private MasterRepository masterRepository;
    @Autowired
    private OrderRepository orderRepository;


    public void makeReview(Order order, Master master, String reviewText, String reviewStars) {
        int stars = Integer.parseInt(reviewStars);

        order.setReviewStars(stars);
        order.setReviewText(reviewText);
        master.setCompletedOrdersCount(master.getCompletedOrdersCount()+1);
        master.setStarsCount(master.getStarsCount()+stars);

        orderRepository.save(order);
        masterRepository.save(master);
    }


}
