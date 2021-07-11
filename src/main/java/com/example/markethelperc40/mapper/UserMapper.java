package com.example.markethelperc40.mapper;

import com.example.markethelperc40.dto.UserRegistrationDto;
import com.example.markethelperc40.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRegistrationDto dto);

}
