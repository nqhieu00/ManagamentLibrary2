package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.common.ERole;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleResponse {
    private Long id;
    private ERole name;
}
