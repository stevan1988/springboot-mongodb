package com.todcode.restmongodb;

import com.todcode.restmongodb.api.dto.type.RoleType;
import com.todcode.restmongodb.api.service.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestMongodbApplication extends SpringBootServletInitializer {

    @SuppressWarnings("resource")
    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RestMongodbApplication.class, args);

        context.getBean(RoleService.class).addRole(RoleType.ROLE_ADMIN);
        context.getBean(RoleService.class).addRole(RoleType.ROLE_USER);

    }
}
