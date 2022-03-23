package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.entity.Base;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class BookResponse extends Base {
    private String name;
    private String image;
    private Long count;
    private Long publishAt;
    private String content;
    private CategoryResponse category;
    private PublisherResponse publisher;
    private Set<AuthorResponse> authors;

}
