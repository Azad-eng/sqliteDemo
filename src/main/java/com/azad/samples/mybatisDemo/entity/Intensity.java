package com.azad.samples.mybatisDemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: EFL-ryl
 */
@Data
@TableName("intensityRecord")
public class Intensity {
    private Long id;
    private String openTime;
    private String closeTime;
    private float time;
    private double intensity;

    @TableField(exist = false)
    private String ignoreColumn = "ignoreColumn";

    @TableField(exist = false)
    private Integer count;
}
