package com.example.managementlibrary.dto.response;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long id;
    private Long cartId;
    private BookResponse book;
}
