package com.accidpayments.microservices.data_access_layer.service;

import com.accidpayments.microservices.data_access_layer.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int userId);

    User save(User user);

    void deleteById(int userId);
}
