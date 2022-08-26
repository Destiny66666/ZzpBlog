package com.zzp.service;

import com.zzp.pojo.MessageBoard;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:56
 */
public interface MessageBoardService {

    /**
     * 根据没有继承父ID查询留言信息
     */
    List<MessageBoard> queryMessageBoardByextendsCommentIdIsZero();

    /**
     * 查询留言条数
     */
    int queryMessageBoardCount();

    /**
     * 根据ID删除留言
     */
    int deleteMessageBoardById(int id);

    /**
     * 根据父ID查询结果
     */
    List<MessageBoard> queryMessageBoardByextendsCommentId(int extendsMessageBoardId);

    /**
     * 增加留言板评论
     */
    int addMessageBoard(MessageBoard messageBoard);

    /**
     * 增加留言板评论
     */
    List<MessageBoard> queryMessageBoardAll();
}
