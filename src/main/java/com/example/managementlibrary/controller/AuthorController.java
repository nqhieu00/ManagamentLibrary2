package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/authors")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
public class AuthorController extends GenericAPI<Author,Long, AuthorRequest, AuthorResponse>{


    public AuthorController(GenericService<Author, Long, AuthorRequest, AuthorResponse> genericService) {
        super(genericService);
    }

    @Override
    public ResponseEntity<Page<AuthorResponse>> getPage(@RequestParam(value = "page") Integer page,
                                                        @RequestParam(value = "limit") Integer limit,
                                                        @RequestParam(value = "sort") String sort,
                                                        @RequestParam(value = "filter-field",required = false) String field,
                                                        @RequestParam(value = "filter-operator",required = false) String operator,
                                                        @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<AuthorResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<AuthorResponse> create(@Validated @RequestBody AuthorRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<AuthorResponse> update(@Validated @RequestBody AuthorRequest dto, @PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
