package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.CategoryMapper;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.CategoryRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryServiceImpl extends GenericServiceImp<Category,Long, CategoryRequest, CategoryResponse> implements CategoryService {
    public CategoryServiceImpl(GenericRepository<Category, Long> repository) {
        super(repository);
    }

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new GenericException("Category with id = "+id +" does not exist "));
        category.getBooks().forEach(b->b.setCategory(null));
        bookRepository.saveAll(category.getBooks());
        super.delete(id);
    }

    @Override
    public Category transformDTOToEntity(CategoryRequest element) {
        return categoryMapper.dtoToEntity(element);
    }

    @Override
    public CategoryResponse transformEntityToDTO(Category element) {
        return categoryMapper.entityToDto(element);
    }
}
