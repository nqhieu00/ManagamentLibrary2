package com.example.managementlibrary.repository;


import com.example.managementlibrary.entity.User;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;


public interface UserRepository extends GenericRepository<User,Long> {


    @Query(value = "SELECT * from user as e where binary e.email=?1",nativeQuery = true)
    User findByEmail(String email);
    Optional<User> findByToken(String token);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);





}
