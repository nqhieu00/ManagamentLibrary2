package com.example.managementlibrary.dto.request;

import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CartRequest {

    @NotNull
    @Min(value = 1)
    private Long id;
    private List<CartItemRequest> cartItems;
}
