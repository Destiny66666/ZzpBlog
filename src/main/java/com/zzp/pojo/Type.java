package com.zzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private int id;
    private String name;
    private int number;
    private Date createTime;
    private Date updateTime;
}
