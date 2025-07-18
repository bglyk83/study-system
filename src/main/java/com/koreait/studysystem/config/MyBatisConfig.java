package com.koreait.studysystem.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.koreait.studysystem.repository")
public class MyBatisConfig {
} 