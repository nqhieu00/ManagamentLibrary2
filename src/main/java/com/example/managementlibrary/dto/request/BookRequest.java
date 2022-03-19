package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class BookRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @Min(value = 1)
    @NotNull
    private Long count;

    @NotNull
    private Long publishAt;

    private String content;

    @NotNull
    private Set<AuthorRequest> authors;
    @Min(value = 1)
    @NotNull
    private Long categoryId;
    @Min(value = 1)
    @NotNull
    private Long publisherId;
}
