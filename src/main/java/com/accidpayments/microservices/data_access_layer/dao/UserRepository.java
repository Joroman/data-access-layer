package com.accidpayments.microservices.data_access_layer.dao;

import com.accidpayments.microservices.data_access_layer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<Entity Type, Primary key type>
public interface UserRepository extends JpaRepository<User, Integer> {
    // give me the crud methods for free not need code
}
