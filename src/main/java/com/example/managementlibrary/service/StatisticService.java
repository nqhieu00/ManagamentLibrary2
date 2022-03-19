package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.BorrowingItem;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface StatisticService {
    HashMap<String,Long> totalMain();
    Map<Long,Long> getBookBorrowedGT();
    List<BookResponse> getBookExist();
    List<BookResponse> getBookBorrowing();
    Map<Long,List<BorrowingItemResponse>> getBookExpired();
}
