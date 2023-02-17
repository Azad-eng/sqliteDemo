package com.azad.samples.mybatisDemo.service;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: EFL-ryl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IntensityMapper userMapper;

    @Override
    public List<Intensity> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public Intensity saveUser(Intensity user) {
        userMapper.insert(user);
        //此时返回user会返回User自动生成的is，MyBatisPlus已经帮我们做好了相关工作
        return user;
    }

    @Override
    public void updateUserById(Intensity user) {
        userMapper.updateById(user);
    }

    @Override
    public void update(Intensity user) {
        //构建条件
        QueryWrapper<Intensity> wrapper = new QueryWrapper<>();
        //根据用户名更新，参数1对应数据库中的字段
        wrapper.eq("user_name","zhangsan");
        //参数1：实体对象
        userMapper.update(user, wrapper);
    }

    //UpdateWrapper和QueryWrapper的区别在于UpdateWrapper可以设置更新的字段，update中一个是对象一个是null
    @Override
    public void update2(Intensity user) {
        //构建条件
        UpdateWrapper<Intensity> wrapper = new UpdateWrapper<>();
        //更改"user_name"为"zhangsan"的age和password字段
        wrapper.set("age",21).set("password","88888").eq("user_name","zhangsan");
        //参数1：实体对象
        userMapper.update(null, wrapper);
    }


    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void delete(Intensity user) {
        QueryWrapper<Intensity> wrapper = new QueryWrapper<>(user);
        userMapper.delete(wrapper);
    }

    @Override
    public void deleteByIds(ArrayList<Integer> ids) {
        userMapper.deleteBatchIds(ids);
    }

    @Override
    public Intensity selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<Intensity> selectByIds(ArrayList<Integer> ids) {
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public Intensity selectOne(Intensity user) {
        return userMapper.selectOne(new QueryWrapper<>(user));
    }

    @Override
    public Long selectCount(Intensity user) {
        return userMapper.selectCount(new QueryWrapper<>(user));
    }

    @Override
    public Long selectCountAll() {
        return userMapper.selectCount(new QueryWrapper<>(null));
    }
}