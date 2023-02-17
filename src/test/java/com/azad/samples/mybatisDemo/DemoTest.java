package com.azad.samples.mybatisDemo;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.mapper.IntensityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: EFL-ryl
 */
@SpringBootTest
public class DemoTest {
    @Autowired
    private IntensityMapper intensityMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Intensity> intensityList = intensityMapper.selectList(null);
        Assertions.assertEquals(4, intensityList.size());
        intensityList.forEach(System.out::println);
    }

    //@Test
    //public void aInsert() {
    //    SysUser user = new SysUser();
    //    user.setName("小羊");
    //    user.setAge(3);
    //    user.setEmail("abc@mp.com");
    //    assertThat(sysUserMapper.insert(user)).isGreaterThan(0);
    //    // 成功直接拿回写的 ID
    //    assertThat(user.getId()).isNotNull();
    //}
    //
    //@Test
    //public void bDelete() {
    //    assertThat(sysUserMapper.deleteById(3L)).isGreaterThan(0);
    //    assertThat(sysUserMapper.delete(new QueryWrapper<SysUser>()
    //            .lambda().eq(SysUser::getName, "Sandy"))).isGreaterThan(0);
    //}
}
