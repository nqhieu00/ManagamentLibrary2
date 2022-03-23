package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;



@RestController
@RequestMapping("/v1/api/auth/statistics")
public class StatisticController  {

    @Autowired
    private StatisticService statisticService;


    @GetMapping
    public ResponseEntity<HashMap<String,Long>> getTotalMain(){
        return new ResponseEntity<>(statisticService.totalMain(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @GetMapping("/borrowingGt")
    public ResponseEntity<Map<Long,Long>> getBookBorrowedGT(){
        return new ResponseEntity<>(statisticService.getBookBorrowedGT(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @GetMapping("/bookBorrowing")
    public ResponseEntity<Map<Long,Long>> getBookBorrowing(){
        return new ResponseEntity<>(statisticService.getBookBorrowing(),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @GetMapping("/bookExpired")
    public ResponseEntity<Map<Long,List<BorrowingItemResponse>>> getBookExpire(){
        return new ResponseEntity<>(statisticService.getBookExpired(),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @GetMapping("/bookExist")
    public ResponseEntity<List<BookResponse>> getBookExist(){
        return new ResponseEntity<>(statisticService.getBookExist(),HttpStatus.OK);
    }

}
