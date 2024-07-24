package com.accidpayments.microservices.data_access_layer.service;

import com.accidpayments.microservices.data_access_layer.entity.Client;
import com.accidpayments.microservices.data_access_layer.entity.User;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(int clientId);

    Client save(Client client);

    void deleteById(int clientId);

    List<Client> findByFirstName(String firstName);

}
