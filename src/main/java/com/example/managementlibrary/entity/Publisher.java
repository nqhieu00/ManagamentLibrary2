package com.example.managementlibrary.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Publisher extends Base{

    @Column(unique = true)
    private String name;
    private String addr;
    @Column(unique = true)
    private String email;
    private String representative;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

}
