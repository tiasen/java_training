package com.thoughtworks.user.service;


import com.thoughtworks.user.entity.UserEntity;
import com.thoughtworks.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User entityToUser(UserEntity userEntity);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity userToEntity(User user);
}
