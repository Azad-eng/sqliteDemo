package com.azad.samples.mybatisDemo.service.impl;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import com.azad.samples.mybatisDemo.service.IntensityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IntensityServiceImpl extends ServiceImpl<IntensityMapper, Intensity> implements IntensityService {
    public static IntensityServiceImpl intensityServiceImpl;

    public IntensityServiceImpl(){
        intensityServiceImpl = this;
    }
}
