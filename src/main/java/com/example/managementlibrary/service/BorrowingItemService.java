package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.BorrowingItemRequest;
import com.example.managementlibrary.dto.request.BorrowingRequest;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.BorrowingResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;

import javax.xml.crypto.Data;

import java.util.List;
import java.util.Date;
import java.util.Map;

public interface BorrowingItemService extends GenericService<BorrowingItem,Long, BorrowingItemRequest, BorrowingItemResponse> {

   // void createListByDate(List<BorrowingItemRequest> borrowingItemRequests,Long UserId);
    Map<Long,BorrowingItemResponse>  findByPaydayLessThan(Date payday);
    void changeStatusById(Long id,boolean status);
}
