package com.example.managementlibrary;


import com.example.managementlibrary.common.ERole;
import com.example.managementlibrary.common.Mail;
import com.example.managementlibrary.dto.request.RoleRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.entity.Role;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.repository.RoleRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.MailService;
import com.example.managementlibrary.service.StatisticService;
import com.example.managementlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ManagementLibraryApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ManagementLibraryApplication.class, args);
    }
    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }
}
