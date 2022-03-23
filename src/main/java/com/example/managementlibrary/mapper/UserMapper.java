package com.example.managementlibrary.mapper;


import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserRequest userRequest);
    UserResponse entityToDTO(User User);
}
