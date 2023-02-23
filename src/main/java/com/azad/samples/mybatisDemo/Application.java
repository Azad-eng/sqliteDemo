package com.azad.samples.mybatisDemo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.azad.samples.mybatisDemo.mapper")
@Slf4j
public class Application {

    public static ConfigurableApplicationContext ac;
    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
        ac = springApplicationBuilder.headless(false).run(args);
        log.info("ac= " + ac);
        //ac = SpringApplication.run(Application.class, args);
        new TestAppendData().testAutoFill();
    }
}