package com.example.managementlibrary.controller;



import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;

import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;

import com.example.managementlibrary.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/v1/api/users")
@PreAuthorize("hasAnyRole('SUPERADMIN')")
public class UserController extends GenericAPI<User, Long, UserRequest, UserResponse> {


    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public UserController(GenericService<User, Long, UserRequest, UserResponse> genericService) {
        super(genericService);
    }






    @PreAuthorize("hasAnyRole('USER','SUPERADMIN','ADMIN')")
    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestParam("newPassword") String newPassWord,
                                            @RequestParam("oldPassword") String oldPassWord) {
        try {
            userService.updatePassword(id, newPassWord, oldPassWord);
            return new ResponseEntity<>("Change password successfully", HttpStatus.OK);
        } catch (GenericException e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, "Change password failed", e), HttpStatus.CONFLICT);
        }

    }

    @Autowired
    private FilesStorageService storageService;

    @PreAuthorize("hasAnyRole('USER','SUPERADMIN','ADMIN')")
    @PutMapping("/{id}/image")
    public ResponseEntity<String> changeImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            storageService.save(image);
            userService.updateImage(id, image.getOriginalFilename());
            return new ResponseEntity("Upload your image successfully", HttpStatus.OK);
        }
        catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT,"Upload your image failed",e), HttpStatus.CONFLICT);
        }

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @PutMapping("/{id}/logout")
    public ResponseEntity<?> logoutUser(@PathVariable Long id) {
        try {
            refreshTokenService.deleteRefreshToken(id);
            return ResponseEntity.ok("User has successfully logged out from the system!");
        }
        catch (GenericException e) {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Error", e), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Page<UserResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "limit") Integer limit,
                                                       @RequestParam(value = "sort") String sort,
                                                       @RequestParam(value = "filter-field",required = false) String field,
                                                       @RequestParam(value = "filter-operator",required = false) String operator,
                                                       @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public ResponseEntity<UserResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<UserResponse> create(@Validated @RequestBody UserRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<UserResponse> update(@Validated @RequestBody UserRequest dto, @PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
