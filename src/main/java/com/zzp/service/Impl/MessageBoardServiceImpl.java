package com.zzp.service.Impl;

import com.zzp.mapper.MessageBoardMapper;
import com.zzp.pojo.MessageBoard;
import com.zzp.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:08
 */
@Service
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    MessageBoardMapper messageBoardMapper;

    @Override
    public List<MessageBoard> queryMessageBoardByextendsCommentIdIsZero() {
        return messageBoardMapper.queryMessageBoardByextendsCommentIdIsZero();
    }

    @Override
    public int queryMessageBoardCount() {
        return messageBoardMapper.queryMessageBoardCount();
    }

    @Override
    public int deleteMessageBoardById(int id) {
        return messageBoardMapper.deleteMessageBoardById(id);
    }

    @Override
    public List<MessageBoard> queryMessageBoardByextendsCommentId(int extendsCommentId) {
        return messageBoardMapper.queryMessageBoardByextendsCommentId(extendsCommentId);
    }

    @Override
    public int addMessageBoard(MessageBoard messageBoard) {
        return messageBoardMapper.addMessageBoard(messageBoard);
    }

    @Override
    public List<MessageBoard> queryMessageBoardAll() {
        return messageBoardMapper.queryMessageBoardAll();
    }
}
