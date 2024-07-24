package com.accidpayments.microservices.data_access_layer.dao;

import com.accidpayments.microservices.data_access_layer.entity.User;

import java.util.List;

public interface UserDAO {

     List<User> findAll();

     User findById(int userId);

     User save(User user);

     void deleteById(int userId);

}
