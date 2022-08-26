package com.zzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private int id;
    private String name;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private int aid;
    private int extendsCommentId;
    private boolean administrator;

    private List<Comment> list = new ArrayList<>();

    private Article article;

}
