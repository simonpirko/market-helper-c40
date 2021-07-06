package com.example.markethelperc40.controller;


import com.example.markethelperc40.entity.User;
import com.example.markethelperc40.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/User")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> registration(@RequestBody User user){
        Optional<User> foundUser = Optional.ofNullable(userService.findUser(user));
        if(foundUser.isPresent()){
            return new ResponseEntity<>("Login is busy", HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
