package com.azad.samples.mybatisDemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("intensity_record")
public class Intensity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openTime;
    private String closeTime;
    private float time;
    private double intensity;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
