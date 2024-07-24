package com.accidpayments.microservices.data_access_layer.service;

import com.accidpayments.microservices.data_access_layer.dao.UserDAO;
import com.accidpayments.microservices.data_access_layer.dao.UserRepository;
import com.accidpayments.microservices.data_access_layer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //best practice use transactional annotation on your service and delete from your DAO
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int userId) {
        //Optional different pattern instead of having to check for nulls
        Optional<User> result = userRepository.findById(userId);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        }else{
            throw new RuntimeException("Did not find employee id - " + userId);
        }
        return user;
    }
    @Override
    //when use Jpa repository no need to use the annotation Transactional
    //@Transactional
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
