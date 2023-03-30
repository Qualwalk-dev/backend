package com.qualwalk.backend;

import org.apache.ibatis.annotations.*;
import org.keycloak.adapters.springboot.*;
import org.mybatis.spring.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.context.annotation.*;

@ComponentScan(basePackages = {
		"com.swantech.security.*",
		"com.qualwalk.backend.*",

		"com.swantech.lang.*",
})
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
		, KeycloakAutoConfiguration.class
		, FlywayAutoConfiguration.class
})
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
