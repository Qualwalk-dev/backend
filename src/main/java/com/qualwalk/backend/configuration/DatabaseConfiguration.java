package com.qualwalk.backend.configuration;

import com.zaxxer.hikari.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.*;
import org.mybatis.spring.*;
import org.mybatis.spring.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.web.*;

import javax.sql.*;

@Configuration
@MapperScan(basePackages = {"com.qualwalk.backend.repository"}, annotationClass = Mapper.class)
public class DatabaseConfiguration {

    @Value("${postgres.url}")
    private String url;

    @Value("${postgres.username}")
    private String username;

    @Value("${postgres.password}")
    private String password;

    @Value("${hikari.idle-timeout:600000}")
    private Integer idleTimeout;

    @Value("${hikari.connection-timeout:600000}")
    private Integer connectionTimeout;

    @Value("${hikari.max-lifetime:3600000}")
    private Integer maxLifetime;

    @Value("${hikari.minimum-idle:50}")
    private Integer minIdleTime;

    @Value("${hikari.maximum-pool-size:400}")
    private Integer maxPoolSize;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setAutoCommit(false);
        hikariConfig.setMinimumIdle(minIdleTime);
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SqlSessionFactoryBean sessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    public SecurityFilterChain customQualwalkSecurity(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests()
//                .antMatchers("/course/categories").permitAll()
//                .anyRequest().authenticated();
//        return httpSecurity.build();
//    }

}
