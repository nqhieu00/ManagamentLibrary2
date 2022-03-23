package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;

public interface UserService extends GenericService<User,Long, UserRequest, UserResponse> {

    void updateResetPasswordToken(String token, String email);
    void updatePassword(String token, String newPassword);
    void registerAccount(String token,UserRequest userRequest);
    void verify(String token);
    void updatePassword(Long userId, String newPassword, String oldPassword);
    void updateImage(Long id,String img);
}
