package com.azad.samples.mybatisDemo.service;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IntensityService extends IService<Intensity> {
    /**
     * 仅有一条数据
     * @param wrapper
     * @return
     */
    default Intensity getOnly(LambdaQueryWrapper<Intensity> wrapper){
        wrapper.last("limit 1");
        wrapper.orderByDesc(Intensity::getId);
        //wrapper.orderByAsc(Intensity::getId);
        return this.getOne(wrapper);
    }
}
