package com.accidpayments.microservices.data_access_layer.service;

import com.accidpayments.microservices.data_access_layer.dao.ClientRepository;
import com.accidpayments.microservices.data_access_layer.entity.Client;
import com.accidpayments.microservices.data_access_layer.entity.User;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService{

    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int clientId) {
        Optional<Client> result = clientRepository.findById(clientId);

        Client client =null;

        if(result.isPresent()){
            client = result.get();
        }else{
            throw new RuntimeException("Did not find client id - " + clientId);
        }

        return client;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(int clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        return clientRepository.findByFirstName(firstName);
    }

}
