package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.PublisherRequest;
import com.example.managementlibrary.dto.response.PublisherResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Publisher;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.PublisherMapper;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.repository.PublisherRepository;
import com.example.managementlibrary.service.GenericService;
import com.example.managementlibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PublisherServiceImpl extends GenericServiceImp<Publisher,Long, PublisherRequest, PublisherResponse> implements PublisherService {
    public PublisherServiceImpl(GenericRepository<Publisher, Long> repository) {
        super(repository);
    }


    @Autowired
    PublisherMapper publisherMapper;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        Publisher publisher=publisherRepository.findById(id).orElseThrow(()->new GenericException("Publisher with id = "+id+" does not exist"));
        publisher.getBooks().forEach(b->b.setPublisher(null));
        bookRepository.saveAll(publisher.getBooks());
        super.delete(id);
    }



    @Override
    public Publisher transformDTOToEntity(PublisherRequest element) {
        return publisherMapper.dtoToEntity(element);
    }

    @Override
    public PublisherResponse transformEntityToDTO(Publisher element) {
        return publisherMapper.entityToDto(element);
    }
}
