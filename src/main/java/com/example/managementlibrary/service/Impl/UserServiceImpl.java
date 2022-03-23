package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;

import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.UserMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.repository.RoleRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.FilesStorageService;
import com.example.managementlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Autowired
    FilesStorageService storageService;

    @Override
    @Transactional
    public UserResponse create(UserRequest element) {


        element.setPassword(passwordEncoder.encode(element.getPassword()));
        User User = transformDTOToEntity(element);
        User.addCart();
        validationUser(User);



        return transformEntityToDTO(userRepository.save(User));
    }

    private void validationUser(User User) {
        if(userRepository.existsByEmail(User.getEmail())&&userRepository.existsByPhone(User.getPhone())) {
            throw new GenericException("Email: đã tồn tại,Phone number: đã tồn tại");
        }
        else if(userRepository.existsByEmail(User.getEmail())){
            throw new GenericException("Email: đã tồn tại");
        }
        else if(userRepository.existsByPhone(User.getPhone())){
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
        User User = userRepository.findByEmail(email);
        if (User != null && User.isNoneLocked()) {
            User.setToken(token);
            /*    try {*/
            userRepository.save(User);
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
        User User = userRepository.findByToken(token).orElseThrow(() -> new GenericException("Could not find user with the token " + token));
        newPassword = passwordEncoder.encode(newPassword);
        User.setPassword(newPassword);
        User.setToken(null);
        //    try {
        userRepository.save(User);
      /*  }catch (Exception e){
            throw new GenericException("Error occurred when adding");
        }*/


    }

    @Override
    public void registerAccount(String token, UserRequest userRequest) {
        User User = transformDTOToEntity(userRequest);
        User.setToken(token);
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        User.addCart();
        validationUser(User);
        userRepository.save(User);


    }

    public void verify(String token) {
        User User = userRepository.findByToken(token).orElseThrow(() -> new GenericException("Could not find user with the token " + token));
        if (!User.isNoneLocked()) {
            User.setToken(null);
            User.setNoneLocked(true);
            /*   try {*/
            userRepository.save(User);
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
        User User = userRepository.findById(userId).orElseThrow(() -> new GenericException("Could not find any customer with id " + userId));
        if (passwordEncoder.matches(oldPassword, User.getPassword())) {
            newPassword = passwordEncoder.encode(newPassword);
            User.setPassword(newPassword);
            /* try {*/
            userRepository.save(User);
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
        User User = userRepository.findById(id).orElseThrow(() -> new GenericException("User with id = " + id + "not exists"));
        try {
            Resource file = storageService.load(User.getImg());
            file.getFile().delete();
        }
        catch (Exception e){
            //throw new GenericException("Change your image failed");
        }
        User.setImg(img);
        userRepository.save(User);
    }

}
