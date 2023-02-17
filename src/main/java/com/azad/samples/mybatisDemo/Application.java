package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.azad.samples.mybatisDemo.mapper")
public class Application {
    @Autowired
    private IntensityMapper intensityMapper;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}