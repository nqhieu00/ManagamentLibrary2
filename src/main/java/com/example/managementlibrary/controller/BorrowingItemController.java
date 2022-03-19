package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.service.BorrowingItemService;
import com.example.managementlibrary.service.GenericService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/borrowingItems")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
public class BorrowingItemController extends GenericAPI<BorrowingItem,Long, BorrowingItemRequest, BorrowingItemResponse> {
    public BorrowingItemController(GenericService<BorrowingItem, Long, BorrowingItemRequest, BorrowingItemResponse> genericService) {
        super(genericService);
    }



    @Autowired
    private BorrowingItemService borrowingItemService;

 /*   @PostMapping("/list")
    public ResponseEntity<?> createListBorrowingItem(@Validated @RequestBody List<BorrowingItemRequest> borrowingItemRequests,
                                                     @RequestParam("userId") Long id){
        try {
            borrowingItemService.createListByDate(borrowingItemRequests,id);
            return new ResponseEntity<>("Create List Item successfully", HttpStatus.OK);
        }catch (GenericException e){
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT,"Create List failed",e), HttpStatus.CONFLICT);
        }
    }*/
    @PutMapping("{id}/status")
    public ResponseEntity<String> changeStatus(@PathVariable Long id,@RequestParam("status") Boolean status){
        try {
            borrowingItemService.changeStatusById(id,status);
            return new ResponseEntity("You changed status successfully", HttpStatus.OK);

        }
        catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT,"You change status failed",e), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/expires")
    public ResponseEntity<Map<Long, BorrowingItemResponse>> findByPaydayLessThan(){
        try {
            Date date = new Date();
            return new ResponseEntity<>(borrowingItemService.findByPaydayLessThan(date), HttpStatus.OK);
        }catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT,"Create List failed",e), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<Page<BorrowingItemResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                                @RequestParam(value = "limit") Integer limit,
                                                                @RequestParam(value = "sort") String sort,
                                                                @RequestParam(value = "filter-field",required = false) String field,
                                                                @RequestParam(value = "filter-operator",required = false) String operator,
                                                                @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<BorrowingItemResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<BorrowingItemResponse> create(@Validated @RequestBody BorrowingItemRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<BorrowingItemResponse> update(@Validated @RequestBody BorrowingItemRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
