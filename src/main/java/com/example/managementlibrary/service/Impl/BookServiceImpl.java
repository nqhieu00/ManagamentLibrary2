package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.BookRequest;
import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.mapper.BookMapper;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends GenericServiceImp<Book,Long, BookRequest, BookResponse> implements BookService {
    public BookServiceImpl(GenericRepository<Book, Long> repository) {
        super(repository);
    }

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book transformDTOToEntity(BookRequest element) {
        return bookMapper.dtoToEntity(element);
    }

    @Override
    public BookResponse transformEntityToDTO(Book element) {
        return bookMapper.entityToDto(element);
    }

    @Override
    public List<BookResponse> findByCategoryId(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId).stream().map(e->transformEntityToDTO(e)).collect(Collectors.toList());
    }
}