package com.example.managementlibrary.repository;

import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Publisher;

import java.util.List;

public interface BookRepository extends GenericRepository<Book,Long> {
    List<Book> findByCategoryId(Long categoryId);

}
