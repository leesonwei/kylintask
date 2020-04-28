package com.delta.report.kylintask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.delta.report.kylintask.dao")
public class KylintaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(KylintaskApplication.class, args);
    }

}
