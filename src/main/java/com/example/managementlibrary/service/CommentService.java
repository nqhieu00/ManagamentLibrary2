package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService extends GenericService<Comment,Long, CommentRequest, CommentResponse>{

    Page<CommentResponse> findByParentExistsAndBookId(Long bookId, Integer page, Integer limit);
}
