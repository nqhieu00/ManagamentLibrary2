package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.service.BookService;
import com.example.managementlibrary.service.GenericService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/books")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
public class BookController extends GenericAPI<Book,Long, BookRequest, BookResponse> {
    public BookController(GenericService<Book, Long, BookRequest, BookResponse> genericService) {
        super(genericService);
    }



    @Autowired
    private BookService bookService;

    @GetMapping("/categories/{id}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<BookResponse>> findByCategoryId(@PathVariable Long id){
        return new ResponseEntity<>(bookService.findByCategoryId(id), HttpStatus.OK);
    }


    @Override
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Page<BookResponse>> getPage(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "limit") Integer limit,
                                                      @RequestParam(value = "sort") String sort,
                                                      @RequestParam(value = "filter-field",required = false) String field,
                                                      @RequestParam(value = "filter-operator",required = false) String operator,
                                                      @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<BookResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<BookResponse> create(@Validated @RequestBody BookRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<BookResponse> update(@Validated @RequestBody BookRequest dto, @PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
