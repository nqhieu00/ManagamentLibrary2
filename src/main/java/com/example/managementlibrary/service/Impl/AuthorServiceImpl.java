package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.AuthorMapper;
import com.example.managementlibrary.repository.AuthorRepository;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthorServiceImpl extends GenericServiceImp<Author,Long, AuthorRequest, AuthorResponse> implements AuthorService {
    public AuthorServiceImpl(GenericRepository<Author, Long> repository) {
        super(repository);
    }

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Override
    public Author transformDTOToEntity(AuthorRequest element) {
        return authorMapper.dtoToEntity(element);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Author author=authorRepository.findById(id).orElseThrow(()->new GenericException("Author with id = "+id+" does not exist"));
        author.getBooks().forEach(b->b.getAuthors().remove(author));
        bookRepository.saveAll(author.getBooks());
        super.delete(id);
    }

    @Override
    public AuthorResponse transformEntityToDTO(Author element) {
        return authorMapper.entityToDTO(element);
    }
}
