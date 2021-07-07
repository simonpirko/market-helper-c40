package com.example.markethelperc40.repository;

import com.example.markethelperc40.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


}
