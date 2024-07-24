package com.accidpayments.microservices.data_access_layer.rest;

import com.accidpayments.microservices.data_access_layer.dao.UserDAO;
import com.accidpayments.microservices.data_access_layer.entity.User;
import com.accidpayments.microservices.data_access_layer.service.UserService;
import com.accidpayments.microservices.data_access_layer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class UserRestController {

    private UserService userService;

    //constructor injection
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findById(@PathVariable int userId){
        User user = userService.findById(userId);
        if(user == null){
            throw new RuntimeException("User Id not found - "+ userId);
        }

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody  User user){
        user.setId(0);
        User dbUser = userService.save(user);
        return dbUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        User dbUser = userService.save(user);
        return dbUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUserById(@PathVariable int userId){
        User tempUser = userService.findById(userId);

        if(tempUser == null){
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteById(userId);

        return "Deleted Employee id - " + userId;
    }
}
