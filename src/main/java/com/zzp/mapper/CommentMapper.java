package com.zzp.mapper;

import com.zzp.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:45
 */
@Repository
@Mapper
public interface CommentMapper {

    /**
     * 根据博客ID获取没有父级的评论
     */
    List<Comment> queryCommentByAidAndExtendsCommentIdIsZero(@Param("aid") int aid);

    /**
     * 保存评论
     */
    int addComment(Comment comment);

    /**
     * 根据父ID查询结果
     */
    List<Comment> queryCommentByExtendsCommentId(@Param("extendsCommentId") int extendsCommentId);

    /**
     * 查询所有文章所对应的评论
     */
    List<Comment> queryCommentAll();

    /**
     * 根据ID获取评论信息
     */
    Comment queryCommentById(@Param("id") int id);

    /**
     * 根据ID删除评论信息
     */
    int deleteCommentById(@Param("id") int id);

    /**
     * 查询评论数量
     */
    int queryCommentCount();

    /**
     * 根据博客ID删除评论
     */
    int deleteCommentByAid(@Param("aid")int aid);

}
