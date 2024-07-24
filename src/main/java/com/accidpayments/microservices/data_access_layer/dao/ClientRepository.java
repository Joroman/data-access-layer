package com.accidpayments.microservices.data_access_layer.dao;

import com.accidpayments.microservices.data_access_layer.entity.Client;
import com.accidpayments.microservices.data_access_layer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE c.firstName = :firstName")
    List<Client> findByFirstName(@Param("firstName") String firstName);
}
