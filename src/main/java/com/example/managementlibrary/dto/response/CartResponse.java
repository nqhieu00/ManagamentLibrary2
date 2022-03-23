package com.example.managementlibrary.dto.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CartResponse {
    private Long id;
    private List<CartItemResponse> cartItems;
}
