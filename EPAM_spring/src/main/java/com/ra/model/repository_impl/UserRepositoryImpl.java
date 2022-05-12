package com.ra.model.repository_impl;

import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.enums.OrderSort;
import com.ra.model.enums.OrderState;
import com.ra.model.repository.UserRepository;
import com.ra.model.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Component
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> findUsersBySearchString(String searchString) {
        String[] tokens = (searchString==null) ? new String[0] : searchString.trim().split("\\s+");
        String sql = "SELECT * FROM users";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> users = query.getResultList();

        users.removeIf(user -> {
            for (String token : tokens) {
                if (token.isEmpty()) continue;
                if (user.getEmail().toLowerCase().contains(token.toLowerCase())) continue;
                if (user.getFirstname().toLowerCase().contains(token.toLowerCase())) continue;
                if (user.getSurname().toLowerCase().contains(token.toLowerCase())) continue;
                return true;
            }
            return false;
        });

        return users;
    }
}
