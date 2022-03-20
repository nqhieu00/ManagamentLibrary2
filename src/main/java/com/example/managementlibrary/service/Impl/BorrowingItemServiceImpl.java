package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.BorrowingItemMapper;
import com.example.managementlibrary.mapper.BorrowingMapper;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.BorrowingItemRepository;
import com.example.managementlibrary.repository.BorrowingRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.BorrowingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BorrowingItemServiceImpl extends GenericServiceImp<BorrowingItem, Long, BorrowingItemRequest, BorrowingItemResponse> implements BorrowingItemService {
    public BorrowingItemServiceImpl(GenericRepository<BorrowingItem, Long> repository) {
        super(repository);
    }

    @Autowired
    BorrowingItemMapper borrowingItemMapper;

    @Autowired
    BorrowingItemRepository borrowingItemRepository;

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public BorrowingItem transformDTOToEntity(BorrowingItemRequest element) {
        return borrowingItemMapper.dtoToEntity(element);
    }

    @Override
    public BorrowingItemResponse transformEntityToDTO(BorrowingItem element) {
        return borrowingItemMapper.entityToDto(element);
    }

    /*@Override
    @Transactional
    public void createListByDate(List<BorrowingItemRequest> borrowingItemRequests, Long userId) {

        Borrowing borrowingUser = new Borrowing();
        User user = new User();
        user.setId(userId);
        borrowingUser.setUser(user);
        Borrowing borrowing = borrowingRepository.save(borrowingUser);
        borrowingItemRequests.stream().forEach(e -> createItem(e, borrowing.getId()));

    }*/

    @Override
    public Map<Long, BorrowingItemResponse> findByPaydayLessThan(Date payday) {

        Map<Long, BorrowingItemResponse> mapBorrowingItemExpires = new HashMap<>();
        borrowingItemRepository.findByPaydayLessThanAndStatus(payday, false).stream().forEach(e -> addToMap(e, mapBorrowingItemExpires));
        return mapBorrowingItemExpires;
    }

    @Override
    public void changeStatusById(Long id, boolean status) {

        BorrowingItem borrowingItem=borrowingItemRepository.findById(id).orElseThrow(()->new GenericException("Item with id = "+id+" does not exist"));
        Book book=bookRepository.getById(borrowingItem.getBook().getId());
        if(borrowingItem.getStatus()!=status&&borrowingItem.getBorrowing().getStatus()==1){
            borrowingItem.setStatus(status);
            if(status){
                book.setCount(book.getCount()+1);
                bookRepository.save(book);
            }
            else {
                book.setCount(book.getCount()-1);
                bookRepository.save(book);
            }
        }
        else {
            throw new GenericException("The borrowing is waiting for confirm or refuse");
        }

        borrowingItemRepository.save(borrowingItem);
    }

    private void addToMap(BorrowingItem e, Map<Long, BorrowingItemResponse> mapBorrowingItemExpires) {
        mapBorrowingItemExpires.put(e.getBorrowing().getUser().getId(),transformEntityToDTO(e));
    }

    @Override
    @Transactional
    public BorrowingItemResponse update(BorrowingItemRequest element, Long id) {
        BorrowingItemResponse borrowingItemResponse= super.update(element, id);
        if(borrowingItemResponse.getStatus()){
            Book book=bookRepository.getById(element.getBookId());
            book.setCount(book.getCount()+1);
            bookRepository.save(book);
        }
        return borrowingItemResponse;

    }

    @Override
    public BorrowingItemResponse create(BorrowingItemRequest element) {
        Book book=bookRepository.getById(element.getBookId());
        book.setCount(book.getCount()-1);
        bookRepository.save(book);
        element.setStatus(false);
        return super.create(element);
    }

   /* private void createItem(BorrowingItemRequest e, Long id) {
      //  e.setBorrowingId(id);
        e.setStatus(false);
        super.create(e);
        Book book=bookRepository.getById(e.getBookId());
        book.setCount(book.getCount()-1);
        bookRepository.save(book);
    }*/

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            BorrowingItem item=borrowingItemRepository.getById(id);
            if(!item.getStatus()){
                Book book=bookRepository.getById(item.getBook().getId());
                book.setCount(book.getCount()+1);
                bookRepository.save(book);
            }
            super.delete(id);
        }
        catch (Exception e){
            throw new GenericException("Delete failed");
        }

    }
}
