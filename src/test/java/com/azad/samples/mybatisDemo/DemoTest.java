package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import com.azad.samples.mybatisDemo.service.IntensityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DemoTest {
    @Autowired
    private IntensityMapper intensityMapper;

    @Autowired
    private IntensityService intensityService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Intensity> list = intensityService.list();
        list.forEach(System.out::println);
    }
    @Test
    public void testWrapper() {
        try {
            System.out.println(intensityService.getOnly(new LambdaQueryWrapper<>()).getId());
        } catch (Exception e) {
            Intensity intensityEntity = new Intensity();
            intensityEntity.setOpenTime("001");
            intensityEntity.setCloseTime("002");
            intensityEntity.setTime(3);
            intensityEntity.setIntensity(1.2);
            if (intensityService.save(intensityEntity)) {
                intensityService.list().forEach(System.out::println);
            } else {
                System.out.println("添加数据失败");
            }
        }
    }

    @Test
    public void testAutoFill() {
        Intensity intensityEntity = new Intensity();
        intensityEntity.setOpenTime("001");
        intensityEntity.setCloseTime("002");
        intensityEntity.setTime(3);
        intensityEntity.setIntensity(1.2);
        if (intensityService.save(intensityEntity)) {
            intensityService.list().forEach(System.out::println);
        } else {
            System.out.println("添加数据失败");
        }
    }
}
