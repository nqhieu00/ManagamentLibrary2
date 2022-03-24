package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Book extends Base{

    private String name;
    private String image;
    @Min(value = 0)
    private Long count;
    private Long publishAt;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id")
            , inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CartItem> cartItems=new ArrayList<>();

    @OneToMany(mappedBy = "book",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<BorrowingItem> borrowingItems=new ArrayList<>();

    @OneToMany(mappedBy = "book",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<>();




}
