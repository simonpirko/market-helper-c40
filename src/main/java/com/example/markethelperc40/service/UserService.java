package com.example.markethelperc40.service;

import com.example.markethelperc40.dto.UserRegistrationDto;
import com.example.markethelperc40.entity.User;
import com.example.markethelperc40.entity.UserRole;
import com.example.markethelperc40.entity.UserStatus;
import com.example.markethelperc40.exception.UserEmailExistsException;
import com.example.markethelperc40.mapper.UserMapper;
import com.example.markethelperc40.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User registration(UserRegistrationDto dto){
        User user = UserMapper.INSTANCE.toUser(dto);

        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserEmailExistsException();
        }

        user.setStatus(UserStatus.BLOCKED);
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return user;
    }

}
