package com.accidpayments.microservices.data_access_layer.dao;

import com.accidpayments.microservices.data_access_layer.entity.User;

public interface UserDAO {

    public void save(User user);
}
