package com.zzp.service.Impl;

import com.zzp.mapper.CommentMapper;
import com.zzp.pojo.Comment;
import com.zzp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:06
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public List<Comment> queryCommentByAidAndExtendsCommentIdIsZero(int aid) {
        return commentMapper.queryCommentByAidAndExtendsCommentIdIsZero(aid);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> queryCommentByExtendsCommentId(int extendsCommentId) {
        return commentMapper.queryCommentByExtendsCommentId(extendsCommentId);
    }

    @Override
    public List<Comment> queryCommentAll() {
        return commentMapper.queryCommentAll();
    }

    @Override
    public Comment queryCommentById(int id) {
        return commentMapper.queryCommentById(id);
    }

    @Override
    public int deleteCommentById(int id) {
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public int queryCommentCount() {
        return commentMapper.queryCommentCount();
    }

    @Override
    public int deleteCommentByAid(int aid) {
        return commentMapper.deleteCommentByAid(aid);
    }


}
