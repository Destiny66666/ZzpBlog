package com.zzp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBoard {

    private int id;
    private String name;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private int extendsCommentId;
    private boolean administrator;

    private List<MessageBoard> list = new ArrayList<>();

}
