package com.example.markethelperc40.service;

import com.example.markethelperc40.entity.User;
import com.example.markethelperc40.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User findUser(User user){
        return userRepository.findByEmail(user.getEmail());
    }
}
