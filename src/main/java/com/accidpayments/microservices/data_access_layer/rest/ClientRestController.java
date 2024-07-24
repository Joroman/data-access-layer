package com.accidpayments.microservices.data_access_layer.rest;

import com.accidpayments.microservices.data_access_layer.entity.Client;
import com.accidpayments.microservices.data_access_layer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    ClientService clientService;
    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientService.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public Client getClientById(@PathVariable int clientId){
        Client client = clientService.findById(clientId);
        if(client == null){
            throw new RuntimeException("User Id not found - "+ clientId);
        }

        return client;
    }

    @PostMapping("/clients")
    public Client addClient(@RequestBody Client client){
        client.setId(0);
        Client dbClient = clientService.save(client);
        return dbClient;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client client){
        Client dbClient = clientService.save(client);
        return dbClient;
    }

    @DeleteMapping("/clients/{clientId}")
    public String deleteById(@PathVariable int clientId){
        Client deleteClient = clientService.findById(clientId)

        if(deleteClient == null){
            throw new RuntimeException("Client id not found " + clientId);
        }

        clientService.deleteById(clientId);

        return "Deleted Employee id - " + clientId;
    }
    @GetMapping("/client/first_name/{firsName}")
    public List<Client> findByFirstName(String firstName){
        List <Client> clientList = clientService.findByFirstName(firstName);
        if(clientList.size() == 0){
            throw new RuntimeException("Client fisrt name not found "+ firstName);
        }

        return clientList;
    }
}
