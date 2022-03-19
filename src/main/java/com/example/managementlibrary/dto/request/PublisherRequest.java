package com.example.managementlibrary.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PublisherRequest {

    private Long id;
    @NotBlank
    private String name;
}
