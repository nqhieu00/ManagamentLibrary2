package com.example.managementlibrary.service.Impl;


import com.example.managementlibrary.dto.response.BookResponse;
import com.example.managementlibrary.dto.response.BorrowingItemResponse;
import com.example.managementlibrary.mapper.BookMapper;
import com.example.managementlibrary.mapper.BorrowingItemMapper;
import com.example.managementlibrary.mapper.UserMapper;
import com.example.managementlibrary.repository.BookRepository;
import com.example.managementlibrary.repository.BorrowingItemRepository;
import com.example.managementlibrary.repository.CommentRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.BorrowingItemService;
import com.example.managementlibrary.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;



@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    BorrowingItemRepository borrowingItemRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BorrowingItemService borrowingItemService;
    @Override
    public HashMap<String, Long> totalMain() {
        Long totalBook=bookRepository.count();
        Long totalUser=userRepository.count();
        Long totalComment=commentRepository.count();
        HashMap<String,Long> mapTotal=new HashMap<>();
        mapTotal.put("user",totalUser);
        mapTotal.put("book",totalBook);
        mapTotal.put("comment",totalComment);
        return mapTotal;
    }

    @Override
    public Map<Long,Long> getBookBorrowedGT() {

        Map<Long,Long> map=new HashMap();
        borrowingItemRepository.getBookBorrowing().stream().forEach(e->addMap(e,map));
        Map<Long,Long> sortMap=map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortMap;


    }

    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BorrowingItemMapper borrowingItemMapper;


    @Override
    public List<BookResponse> getBookExist() {
       return bookRepository.findAll().stream().filter(e->e.getCount()>0)
               .map(e->bookMapper.entityToDto(e))
               .collect(Collectors.toList());
    }

    @Override
    public Map<Long,Long> getBookBorrowing() {
        Map<Long,Long> map=new HashMap();
        borrowingItemRepository.getBookBorrowing().stream().forEach(e->addMap(e,map));


        return map;
    }


    @Override
    public Map<Long, List<BorrowingItemResponse>> getBookExpired() {
        Date date=new Date();
        Map<Long,List<BorrowingItemResponse>> map=new HashMap<>();
        borrowingItemRepository.getUserBookExpired(date,false).forEach(e->addMap(e.getId(),map,date,false));
        return map;
    }

    private void addMap(Long id, Map<Long, List<BorrowingItemResponse>> map, Date date, boolean b) {
        List<BorrowingItemResponse>list=borrowingItemRepository.findByPaydayLessThanAndStatusAndBorrowing_UserId(date,b,id).stream().filter(e->e.getBorrowing().getStatus()==1)
                .map(e->borrowingItemMapper.entityToDto(e)).collect(Collectors.toList());
        map.put(id,list);
    }



    private void addMap(Long[] e, Map map) {
        map.put( (e[0]),e[1]);
    }


}
