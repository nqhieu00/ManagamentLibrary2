package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.common.Filter;
import com.example.managementlibrary.dto.request.RoleRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;

import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.UserMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.repository.RoleRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.UserService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class UserServiceImpl extends GenericServiceImp<User, Long, UserRequest, UserResponse> implements UserService {
    public UserServiceImpl(GenericRepository<User, Long> repository) {
        super(repository);
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public User transformDTOToEntity(UserRequest element) {
        return userMapper.dtoToEntity(element);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse create(UserRequest element) {


        element.setPassword(passwordEncoder.encode(element.getPassword()));
        User user = transformDTOToEntity(element);
        user.addCart();
        validationUser(user);



        return transformEntityToDTO(userRepository.save(user));
    }

    private void validationUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())&&userRepository.existsByPhone(user.getPhone())) {
            throw new GenericException("Email: đã tồn tại,Phone number: đã tồn tại");
        }
        else if(userRepository.existsByEmail(user.getEmail())){
            throw new GenericException("Email: đã tồn tại");
        }
        else if(userRepository.existsByPhone(user.getPhone())){
            throw new GenericException("Phone number: đã tồn tại");
        }
    }


    @Override
    @Transactional
    public UserResponse update(UserRequest element, Long id) {
        // setRoles(element.getRoles());

        if (!checkBcrypt(element.getPassword())) {
            element.setPassword(passwordEncoder.encode(element.getPassword()));
        }
        return super.update(element, id);
    }

    private boolean checkBcrypt(String password) {
        if (password.length() >= 59) {
            return true;
        }
        return false;
    }

    @Override
    public UserResponse transformEntityToDTO(User element) {
        return userMapper.entityToDTO(element);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.isNoneLocked()) {
            user.setToken(token);
            /*    try {*/
            userRepository.save(user);
            // }
           /* catch (Exception e){
                throw new GenericException("Error occurred when adding");
            }
*/
        } else {
            throw new GenericException("Could not find any customer with the email " + email);
        }
    }

    @Override
    public void updatePassword(String token, String newPassword) {
        User user = userRepository.findByToken(token).orElseThrow(() -> new GenericException("Could not find user with the token " + token));
        newPassword = passwordEncoder.encode(newPassword);
        user.setPassword(newPassword);
        user.setToken(null);
        //    try {
        userRepository.save(user);
      /*  }catch (Exception e){
            throw new GenericException("Error occurred when adding");
        }*/


    }

    @Override
    public void registerAccount(String token, UserRequest userRequest) {
        User user = transformDTOToEntity(userRequest);
        user.setToken(token);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addCart();
        validationUser(user);
        userRepository.save(user);


    }

    public void verify(String token) {
        User user = userRepository.findByToken(token).orElseThrow(() -> new GenericException("Could not find user with the token " + token));
        if (!user.isNoneLocked()) {
            user.setToken(null);
            user.setNoneLocked(true);
            /*   try {*/
            userRepository.save(user);
         /*   }
            catch (Exception e){
                throw new GenericException("Error occurred when adding");
            }*/

        } else {
            throw new GenericException("Invalid token " + token);
        }
    }

    @Override
    public void updatePassword(Long userId, String newPassword, String oldPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GenericException("Could not find any customer with id " + userId));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            newPassword = passwordEncoder.encode(newPassword);
            user.setPassword(newPassword);
            /* try {*/
            userRepository.save(user);
          /*  }
            catch (Exception e){
                throw new GenericException("Error occurred when adding");
            }*/

        } else {
            throw new GenericException("Old password is not correct");
        }
    }

    @Override
    public void updateImage(Long id, String img) {
        User user = userRepository.findById(id).orElseThrow(() -> new GenericException("User with id = " + id + "not exists"));
        user.setImg(img);
        userRepository.save(user);
    }

}
