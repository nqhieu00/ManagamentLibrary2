package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.service.BorrowingService;
import com.example.managementlibrary.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/api/borrowings")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
public class BorrowingController extends GenericAPI<Borrowing,Long, BorrowingRequest, BorrowingResponse>{
    public BorrowingController(GenericService<Borrowing, Long, BorrowingRequest, BorrowingResponse> genericService) {
        super(genericService);
    }

    @Autowired
    BorrowingService borrowingService;

    @PutMapping("/{id}/status")
    public ResponseEntity<String> changeStatusById(@PathVariable Long id,@RequestParam("status") int status){
        try {
            borrowingService.changeStatusById(id,status);
            return new ResponseEntity<>("You change status successfully", HttpStatus.OK);
        }catch (GenericException e){
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT,"You change status failed",e), HttpStatus.CONFLICT);
        }
    }


    @Override
    public ResponseEntity<Page<BorrowingResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                            @RequestParam(value = "limit") Integer limit,
                                                            @RequestParam(value = "sort") String sort,
                                                            @RequestParam(value = "filter-field",required = false) String field,
                                                            @RequestParam(value = "filter-operator",required = false) String operator,
                                                            @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<BorrowingResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<BorrowingResponse> create(@Validated @RequestBody BorrowingRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<BorrowingResponse> update(@Validated @RequestBody BorrowingRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
