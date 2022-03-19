package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Category extends Base{

    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();
}
