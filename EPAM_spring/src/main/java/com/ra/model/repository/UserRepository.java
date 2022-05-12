package com.ra.model.repository;

import com.ra.model.entity.Manager;
import com.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, String>, UserRepositoryCustom {

}
