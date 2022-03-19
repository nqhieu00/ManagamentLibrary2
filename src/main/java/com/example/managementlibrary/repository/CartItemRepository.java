package com.example.managementlibrary.repository;

import com.example.managementlibrary.entity.CartItem;

public interface CartItemRepository extends GenericRepository<CartItem,Long> {

    Boolean existsByBookIdAndAndCart_Id(Long bookId,Long userId);
    void deleteByBookIdAndCart_Id(Long bookId,Long userId);
}
