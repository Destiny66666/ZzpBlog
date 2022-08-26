package com.zzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private int id;
    private int tid;
    private int uid;
    private String title;
    private String content;
    private String firstPicture;
    private String firstArticleDescription;
    private String label;
    private boolean draft;
    private boolean shareStatement;
    private boolean deleted;
    private boolean comment;
    private boolean appreciation;
    private int views;
    private int comments;
    private Date createTime;
    private Date updateTime;

    private Type type;
    private User user;

}
