package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import com.azad.samples.mybatisDemo.service.IntensityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoTest {
    @Autowired
    private IntensityMapper intensityMapper;

    @Autowired
    private IntensityService intensityService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        //List<Intensity> list = intensityService.list();
        //List<Intensity> intensityList = intensityMapper.selectList(null);
        //Assertions.assertEquals(1, intensityList.size());
        //list.forEach(System.out::println);
        for (int i = 0; i < 5; i++) {
            Intensity intensityEntity = new Intensity();
            intensityEntity.setOpenTime("o" + i + 1);
            intensityEntity.setCloseTime("c" + i + 2);
            intensityEntity.setTime(i + 3);
            intensityEntity.setIntensity(i * 1.2);
            if (intensityService.save(intensityEntity)) {
                intensityService.list().forEach(System.out::println);
            } else {
                System.out.println("添加数据失败");
            }
        }
    }
    @Test
    public void testUpdate() {
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
