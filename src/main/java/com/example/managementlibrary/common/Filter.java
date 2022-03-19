package com.example.managementlibrary.common;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String field;
    private EOperator operator;
    private String value;
    private List<String> values;

}
