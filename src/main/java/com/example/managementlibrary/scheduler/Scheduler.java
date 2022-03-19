package com.example.managementlibrary.scheduler;

import com.example.managementlibrary.common.Mail;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.entity.Borrowing;
import com.example.managementlibrary.entity.BorrowingItem;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.repository.BorrowingItemRepository;
import com.example.managementlibrary.repository.BorrowingRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.BorrowingItemService;
import com.example.managementlibrary.service.BorrowingService;
import com.example.managementlibrary.service.MailService;
import com.example.managementlibrary.service.StatisticService;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@Component
public class Scheduler {

    //* "0 0 * * * *" = the top of every hour of every day.
    //* "*/10 * * * * *" = every ten seconds.
    //* "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
    //* "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
    //* "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
    //* "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
    //* "0 0 0 25 12 ?" = every Christmas Day at midnight

    // Cron expression is represented by six fields:
    // second, minute, hour, day of month, month, day(s) of week
    //(*) means match any
    //*/X means "every X"

    @Autowired
    MailService mailService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    BorrowingItemService borrowingItemService;

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    StatisticService statisticService;

    @Scheduled(cron = "0 0 1 * * *", zone = "Asia/Saigon")
    public void cronJobSch() throws MessagingException {


        Date date=new Date();
        Map<Long, BorrowingItemResponse> mapBorrowingItemExpires=borrowingItemService.findByPaydayLessThan(date);
        mapBorrowingItemExpires.forEach((k,v)-> {
            try {
                sendMailUser(k,v);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

    }

    private void sendMailUser(Long k, BorrowingItemResponse v) throws MessagingException {
        Mail mail=new Mail();
        User user=userRepository.getById(k);
        mail.setMailFrom("ptdapm60th1@gmail.com");
        mail.setMailTo(user.getEmail());
        mail.setMailSubject("Thong bao");
        String book=v.getBook().getName();
        Date borrowed=borrowingRepository.getById(v.getBorrowingId()).getBorrowedDate();
        Date payday=v.getPayday();
        StringBuffer content=new StringBuffer();
        int days=getDate(payday);
        content.append("Bạn đã mượn sách"+book);
        content.append("vào ngày: "+borrowed.toString()+" có thời gian trả đến ngày: "+payday);
        content.append(".Hiện tại sách của bạn đã quá hạn xin vui lòng trả sách đúng thời hạn quy định!!!");
        mail.setMailContent(content.toString());
        switch (days){
            case 1:
                sendMailUser(mail);
                break;
            case 5:
                sendMailUser(mail);
                break;
            case 10:
                sendMailUser(mail);
                break;
        }


    }

    private void sendMailUser(Mail mail) throws MessagingException {
        mailService.sendEmail(mail);
    }

    private int getDate(Date payday) {
        long difference_In_Time=new Date().getTime()-payday.getTime();
        long difference_In_Days
                = (difference_In_Time
                / (1000 * 60 * 60 * 24))
                % 365;
        return (int) difference_In_Days;
    }

}