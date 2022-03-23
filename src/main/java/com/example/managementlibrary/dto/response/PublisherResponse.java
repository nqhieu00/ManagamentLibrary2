package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.dto.BaseDto;
import com.example.managementlibrary.entity.Base;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PublisherResponse extends BaseDto {
    private String name;
}
