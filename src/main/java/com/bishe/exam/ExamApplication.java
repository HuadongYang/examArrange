package com.bishe.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bishe.exam.dao")
@SpringBootApplication
public class ExamApplication {
    public static void main(String[] args) {

        SpringApplication.run(ExamApplication.class, args);

    }
}
