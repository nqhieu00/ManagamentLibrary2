package com.example.managementlibrary.dto.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItemResponse {
    private Long id;
    private Long cartId;
    private BookResponse book;
}
