package com.ra.model.repository;

import com.ra.model.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findUsersBySearchString(String searchString);
}
