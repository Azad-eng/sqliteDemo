package com.azad.samples.mybatisDemo.service;

import com.azad.samples.mybatisDemo.entity.Intensity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: EFL-ryl
 */
public interface UserService {

    //查询所有用户
    List<Intensity> findAll();

    //保存用户
    Intensity saveUser(Intensity user);

    //更新用户 - 根据id
    void updateUserById(Intensity user);

    //根据条件更新用户
    void update(Intensity user);

    //根据条件更新用户
    void update2(Intensity user);

    //根据id删除
    void deleteById(Integer id);

    //根据用户信息删除
    void delete(Intensity user);

    //根据id集合批量删除
    void deleteByIds(ArrayList<Integer> ids);

    //根据id查询用户
    Intensity selectById(Integer id);

    //根据id集合查询用户
    List<Intensity> selectByIds(ArrayList<Integer> ids);

    //根据条件查询用户
    Intensity selectOne(Intensity user);

    //根据条件查询总记录数 如果查询所有传入空
    Long selectCount(Intensity user);

    //查询所有记录
    Long selectCountAll();
}
