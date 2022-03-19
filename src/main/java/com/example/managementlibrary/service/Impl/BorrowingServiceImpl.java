package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.BorrowingItemMapper;
import com.example.managementlibrary.mapper.BorrowingMapper;
import com.example.managementlibrary.repository.BorrowingItemRepository;
import com.example.managementlibrary.repository.BorrowingRepository;
import com.example.managementlibrary.repository.CartItemRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.BorrowingItemService;
import com.example.managementlibrary.service.BorrowingService;
import com.example.managementlibrary.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowingServiceImpl extends GenericServiceImp<Borrowing,Long, BorrowingRequest, BorrowingResponse> implements BorrowingService {
    public BorrowingServiceImpl(GenericRepository<Borrowing, Long> repository) {
        super(repository);
    }

    @Autowired
    BorrowingMapper borrowingMapper;
    @Autowired
    BorrowingItemRepository borrowingItemRepository;
    @Autowired
    BorrowingItemService borrowingItemService;
    @Autowired
    BorrowingItemMapper borrowingItemMapper;

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    CartItemRepository cartItemRepository;



    @Override
    @Transactional
    public void delete(Long id) {
        borrowingItemRepository.findByBorrowingId(id).stream().forEach(e->deleteItem(e));
        super.delete(id);
    }

    private void deleteItem(BorrowingItem e) {
        borrowingItemService.delete(e.getId());
    }

    @Override
    @Transactional
    public BorrowingResponse create(BorrowingRequest element) {
        element.setStatus(0);
        List<BorrowingItemRequest> borrowingItemRequests=element.getBorrowingItems();
        List<BorrowingItemRequest> list=new ArrayList<>();
        element.setBorrowingItems(list);
        BorrowingResponse response=super.create(element);
        Long userId=element.getUserId();
        Long borrowingId=response.getId();
        borrowingItemRequests.forEach(e->{
            e.setBorrowingId(borrowingId);
            borrowingItemService.create(e);
            cartItemRepository.deleteByBookIdAndCart_Id(e.getBookId(),userId);
         // cartItemService.delete();

        });

        return response;
    }

    @Override
    public Borrowing transformDTOToEntity(BorrowingRequest element) {
        return borrowingMapper.dtoToEntity(element);
    }

    @Override
    public BorrowingResponse transformEntityToDTO(Borrowing element) {
        return borrowingMapper.entityToDto(element);
    }



    @Override
    public void changeStatusById(Long id,int status) {
        Borrowing borrowing=borrowingRepository.findById(id).orElseThrow(()-> new GenericException("Borrowing with id = "+id+" does not exist"));
        borrowing.setStatus(status);
        borrowingRepository.save(borrowing);

    }
}
