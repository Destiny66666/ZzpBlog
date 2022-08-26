package com.zzp.mapper;

import com.zzp.pojo.MessageBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:47
 */
@Repository
@Mapper
public interface MessageBoardMapper {
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
    List<MessageBoard> queryMessageBoardByextendsCommentId(@Param("extendsCommentId") int extendsCommentId);

    /**
     * 增加留言板评论
     */
    int addMessageBoard(MessageBoard messageBoard);


    /**
     * 增加留言板评论
     */
    List<MessageBoard> queryMessageBoardAll();

}
