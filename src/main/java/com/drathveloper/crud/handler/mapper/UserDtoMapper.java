package com.drathveloper.crud.handler.mapper;

import com.drathveloper.crud.handler.dto.GetUserResponse;
import com.drathveloper.crud.handler.dto.RegisterUserRequest;
import com.drathveloper.crud.handler.dto.UpdateUserRequest;
import com.drathveloper.crud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target = "id", ignore = true)
    User registerUserRequestToUser(RegisterUserRequest requestBody);

    GetUserResponse userToGetUserResponse(User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "requestBody.name")
    @Mapping(target = "email", source = "requestBody.email")
    @Mapping(target = "birthDate", source = "requestBody.birthDate")
    User updateUserRequestToUser(long id, UpdateUserRequest requestBody);
}
