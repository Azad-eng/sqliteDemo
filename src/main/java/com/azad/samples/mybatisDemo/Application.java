package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.utils.Excel2DbImporter;
import com.azad.samples.mybatisDemo.utils.SimpleDb2ExcelExporter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("com.azad.samples.mybatisDemo.mapper")
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
        springApplicationBuilder.headless(false).run(args);
        //ac = SpringApplication.run(Application.class, args);
//        new TestAppendData().testAutoFill();
//        new SimpleDb2ExcelExporter().export();
        new Excel2DbImporter().import2Db();
    }
}