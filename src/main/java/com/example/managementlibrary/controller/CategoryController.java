package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;
import com.example.managementlibrary.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/categories")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
public class CategoryController extends GenericAPI<Category,Long, CategoryRequest, CategoryResponse> {
    public CategoryController(GenericService<Category, Long, CategoryRequest, CategoryResponse> genericService) {
        super(genericService);
    }

    @Override
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Page<CategoryResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "limit") Integer limit,
                                                           @RequestParam(value = "sort") String sort,
                                                           @RequestParam(value = "filter-field",required = false) String field,
                                                           @RequestParam(value = "filter-operator",required = false) String operator,
                                                           @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<CategoryResponse> getOne(@PathVariable  Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<CategoryResponse> create(@Validated @RequestBody CategoryRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<CategoryResponse> update(@Validated @RequestBody CategoryRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
