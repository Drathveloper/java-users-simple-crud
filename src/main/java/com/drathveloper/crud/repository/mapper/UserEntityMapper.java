package com.drathveloper.crud.repository.mapper;

import com.drathveloper.crud.model.User;
import com.drathveloper.crud.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity userToUserEntity(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "birthDate", source = "user.birthDate")
    UserEntity userToUserEntity(long id, User user);

    User userEntityToUser(UserEntity userEntity);
}
