package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class BorrowingRequest {
    private Long UserId;
    private Long id;
    private Date borrowedDate;
    private int status;
    private List<BorrowingItemRequest> borrowingItems;

}
