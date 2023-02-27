package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.service.IntensityService;
import com.azad.samples.mybatisDemo.service.impl.IntensityServiceImpl;

/**
 * @author: EFL-ryl
 */
public class TestAppendData {

    public void testAutoFill() {
        IntensityService intensityService = IntensityServiceImpl.intensityServiceImpl;
        Intensity intensityEntity = new Intensity();
        intensityEntity.setOpenTime("222");
        intensityEntity.setCloseTime("333");
        intensityEntity.setTime(3);
        intensityEntity.setIntensity(1.2);
        if (intensityService.save(intensityEntity)) {
            intensityService.list().forEach(System.out::println);
        } else {
            System.out.println("添加数据失败");
        }
    }
}
