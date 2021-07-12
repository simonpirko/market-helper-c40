package com.example.markethelperc40.service;

import com.example.markethelperc40.dto.UserRegistrationDto;
import com.example.markethelperc40.entity.EmailKey;
import com.example.markethelperc40.entity.User;
import com.example.markethelperc40.entity.UserRole;
import com.example.markethelperc40.entity.UserStatus;
import com.example.markethelperc40.exception.UserEmailExistsException;
import com.example.markethelperc40.repository.EmailKeyRepository;
import com.example.markethelperc40.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository repository;
    @Mock private EmailKeyRepository emailKeyRepository;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository, emailKeyRepository);
    }

    @Test
    void canRegistration() {
        UserRegistrationDto dto = new UserRegistrationDto(
                "example@gmail.com",
                "123456789"
        );

        User user = new User(
                1,
                dto.getEmail(),
                dto.getPassword(),
                UserRole.USER,
                UserStatus.BLOCKED
        );

        service.registration(dto);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(repository).save(userArgumentCaptor.capture());

        User captorUser = userArgumentCaptor.getValue();

        assertThat(captorUser).isEqualTo(user);
    }

    @Test
    void failRegistration() {
        UserRegistrationDto dto = new UserRegistrationDto(
                "example@gmail.com",
                "123456789"
        );

        BDDMockito.given(repository.existsByEmail(dto.getEmail()))
                .willReturn(true);

        assertThatThrownBy( () -> service.registration(dto))
                .isInstanceOf(UserEmailExistsException.class);
    }

}