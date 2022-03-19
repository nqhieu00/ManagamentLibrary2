package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CartItemRequest;
import com.example.managementlibrary.dto.response.CartItemResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.entity.CartItem;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.repository.CartItemRepository;
import com.example.managementlibrary.service.CartItemService;
import com.example.managementlibrary.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/cartItems")

public class CartItemController extends GenericAPI<CartItem,Long, CartItemRequest, CartItemResponse> {
    public CartItemController(GenericService<CartItem, Long, CartItemRequest, CartItemResponse> genericService) {
        super(genericService);
    }

    @Autowired
    CartItemService cartItemService;

    @Override
    public ResponseEntity<Page<CartItemResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "limit") Integer limit,
                                                           @RequestParam(value = "sort") String sort,
                                                           @RequestParam(value = "filter-field",required = false) String field,
                                                           @RequestParam(value = "filter-operator",required = false) String operator,
                                                           @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<CartItemResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<CartItemResponse> create(@Validated @RequestBody CartItemRequest dto) {
        Boolean BookExist=cartItemService.checkBookExist(dto.getBookId(),dto.getCartId());
        if(!BookExist){
            return super.create(dto);
        }
        else {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Book exist in your cart", new Exception()),HttpStatus.CONFLICT);
        }

    }

    @Override
    public ResponseEntity<CartItemResponse> update(@Validated @RequestBody CartItemRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }


    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
