package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Publisher;
import com.example.managementlibrary.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/publishers")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
public class PublisherController extends GenericAPI<Publisher,Long, PublisherRequest, PublisherResponse> {
    public PublisherController(GenericService<Publisher, Long, PublisherRequest, PublisherResponse> genericService) {
        super(genericService);
    }

    @Override
    public ResponseEntity<Page<PublisherResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                            @RequestParam(value = "limit") Integer limit,
                                                            @RequestParam(value = "sort") String sort,
                                                            @RequestParam(value = "filter-field",required = false) String field,
                                                            @RequestParam(value = "filter-operator",required = false) String operator,
                                                            @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<PublisherResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<PublisherResponse> create(@Validated @RequestBody PublisherRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<PublisherResponse> update(@Validated @RequestBody PublisherRequest dto, @PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
