package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;
import com.example.managementlibrary.service.CommentService;
import com.example.managementlibrary.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/comments")
@PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
public class CommentController extends GenericAPI<Comment,Long, CommentRequest, CommentResponse> {
    public CommentController(GenericService<Comment, Long, CommentRequest, CommentResponse> genericService) {
        super(genericService);
    }



    @Autowired
    private CommentService commentService;


    @GetMapping("/book/{id}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Page<CommentResponse>> findByParentExistsAndBookId(@PathVariable Long id,
                                                                             @RequestParam(value = "page") Integer page,
                                                                             @RequestParam(value = "limit") Integer limit){
        return new ResponseEntity<>( commentService.findByParentExistsAndBookId(id,page,limit), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CommentResponse>> getPage( @RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "limit") Integer limit,
                                                          @RequestParam(value = "sort") String sort,
                                                          @RequestParam(value = "filter-field",required = false) String field,
                                                          @RequestParam(value = "filter-operator",required = false) String operator,
                                                          @RequestParam(value = "filter-value",required = false) String value) {
        return super.getPage(page, limit, sort, field, operator, value);
    }

    @Override
    public ResponseEntity<CommentResponse> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @Override
    public ResponseEntity<CommentResponse> create(@Validated @RequestBody CommentRequest dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<CommentResponse> update(@Validated @RequestBody CommentRequest dto,@PathVariable Long id) {
        return super.update(dto, id);
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
