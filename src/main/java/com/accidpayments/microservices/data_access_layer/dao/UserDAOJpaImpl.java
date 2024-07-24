package com.accidpayments.microservices.data_access_layer.dao;

import com.accidpayments.microservices.data_access_layer.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOJpaImpl implements UserDAO {
    EntityManager entityManager;
    @Autowired
    public UserDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        //create query Name of the class
        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

        //execute query and get result list
        List<User> user = theQuery.getResultList();

        //return the result
        return user;
    }

    @Override
    public User findById(int userId) {
        return entityManager.find(User.class,userId);
    }

    @Override
    public User save(User user) {
        //use merge if id of object is == 0 save if id contains a value make an update
        return entityManager.merge(user);
    }

    @Override
    public void deleteById(int userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }
}
