package com.example.managementlibrary.service;

import com.example.managementlibrary.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);
    RefreshToken createRefreshToken(Long userId,String jwt);
    RefreshToken verifyExpiration(RefreshToken token);
    void deleteRefreshToken(Long userId);

}
