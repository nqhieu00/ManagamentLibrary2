package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import lombok.Data;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface BorrowingMapper {




    @Mapping(source = "request.userId",target = "user.id")
    Borrowing dtoToEntity(BorrowingRequest request);

    @AfterMapping
    default void setBorrowingItems(BorrowingRequest request, @MappingTarget Borrowing borrowing){
        List<BorrowingItem> borrowingItemList=new ArrayList<>();
        request.getBorrowingItems().forEach(e->{
            BorrowingItem item=new BorrowingItem();
            item.setId(e.getId());
            Borrowing borrowing1=new Borrowing();
            item.setBorrowing(borrowing1);
            Book book=new Book();
            book.setId(e.getBookId());
            item.setBook(book);
            item.setNote(e.getNote());
            item.setStatus(e.getStatus());
            item.setPayday(e.getPayday());
            borrowingItemList.add(item);
            borrowing.setBorrowingItems(borrowingItemList);
        });
    }

    BorrowingResponse entityToDto(Borrowing borrowing);
}
