package com.example.managementlibrary.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class BorrowingResponse {
    private Long id;
    private UserResponse user;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date borrowedDate;
    private int status;
    private List<BorrowingItemResponse> borrowingItems;
}
