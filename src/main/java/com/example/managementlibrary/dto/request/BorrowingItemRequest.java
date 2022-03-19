package com.example.managementlibrary.dto.request;

import com.example.managementlibrary.entity.Book;
import com.example.managementlibrary.entity.Borrowing;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@ToString
public class BorrowingItemRequest {
    private Long id;
    @NotBlank
    private String note;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date payday;
    @NotNull
    private Boolean status;
    @NotNull
    @Min(value = 1)
    private Long borrowingId;

    @NotNull
    @Min(value = 1)
    private Long  bookId;
}
