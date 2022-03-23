package com.example.managementlibrary.repository;

import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.entity.User;
import org.springframework.data.jpa.repository.Query;


import java.util.Date;
import java.util.List;

public interface BorrowingItemRepository extends GenericRepository<BorrowingItem,Long>{

    List<BorrowingItem> findByPaydayLessThanAndStatus(Date payday,Boolean status);
    List<BorrowingItem> findByBorrowingId(Long id);
    @Query("SELECT b.book.id,count(b.book.id) from BorrowingItem as b where b.status=false and b.borrowing.status=1 group by b.book ")
    List<Long[]> getBookBorrowing();
    @Query("SELECT b.user from BorrowingItem as b_i join b_i.borrowing as b  where b_i.payday<?1 and b_i.status=?2  group by b.user")
    List<User> getUserBookExpired(Date payday, Boolean status);

    List<BorrowingItem> findByPaydayLessThanAndStatusAndBorrowing_UserId(Date payday,Boolean status,Long userId);

}
