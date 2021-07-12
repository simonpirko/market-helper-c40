package com.example.markethelperc40.service;

import com.example.markethelperc40.dto.UserRegistrationDto;
import com.example.markethelperc40.entity.EmailKey;
import com.example.markethelperc40.entity.User;
import com.example.markethelperc40.entity.UserRole;
import com.example.markethelperc40.entity.UserStatus;
import com.example.markethelperc40.exception.UserEmailExistsException;
import com.example.markethelperc40.mapper.UserMapper;
import com.example.markethelperc40.repository.EmailKeyRepository;
import com.example.markethelperc40.repository.UserRepository;
import com.example.markethelperc40.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EmailKeyRepository emailKeyRepository;

    @Autowired
    public UserService(UserRepository userRepository, EmailKeyRepository emailKeyRepository) {
        this.userRepository = userRepository;
        this.emailKeyRepository = emailKeyRepository;
    }

    public void registration(UserRegistrationDto dto) {
        User user = UserMapper.INSTANCE.toUser(dto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserEmailExistsException();
        }

        user.setStatus(UserStatus.BLOCKED);
        user.setRole(UserRole.USER);
        userRepository.save(user);

        emailKeyRepository.save(createEmailKey(user));
    }

    private EmailKey createEmailKey(User user) {
        return new EmailKey(
                0L,
                user,
                RandomString.generate(4)
        );
    }

}
