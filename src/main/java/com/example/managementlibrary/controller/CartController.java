package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/carts")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
public class CartController extends GenericAPI<Cart,Long, CartRequest, CartResponse> {
    public CartController(GenericService<Cart, Long, CartRequest, CartResponse> genericService) {
        super(genericService);
    }

    @Override
    public ResponseEntity<Page<CartResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "limit") Integer limit,
                                                       @RequestParam(value = "sort") String sort,
                                                       @RequestParam(value = "filter-field",required = false) String field,
                                                       @RequestParam(value = "filter-operator",required = false) String operator,
                                                       @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<CartResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<CartResponse> create(@Validated @RequestBody CartRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<CartResponse> update(@Validated @RequestBody CartRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
