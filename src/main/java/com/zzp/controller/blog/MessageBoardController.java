package com.zzp.controller.blog;

import com.zzp.pojo.MessageBoard;
import com.zzp.pojo.User;
import com.zzp.service.MessageBoardService;
import com.zzp.util.EmojiUtil;
import com.zzp.util.HtmlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/17 19:47
 */
@Controller
public class MessageBoardController {

    @Autowired
    MessageBoardService messageBoardService;

    @RequestMapping("/messageboard")
    public String messageBoard(Model model, HttpServletRequest request){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null){
            model.addAttribute("loginUser",loginUser);
        }
        return "blog/messageboard";
    }

    @GetMapping("/messageboardcomment")
    @ResponseBody
    public Object messageBoardComment(){
        List<MessageBoard> messageBoards = messageBoardService.queryMessageBoardByextendsCommentIdIsZero();
        querySonMessageBoard(messageBoards);
        return messageBoards;
    }

    //递归查询子评论
    public void querySonMessageBoard(List<MessageBoard> list) {
        if (list.size() > 0) {
            for (MessageBoard messageBoard : list) {
                messageBoard.setContent(EmojiUtil.emojiConverterUnicodeStr(messageBoard.getContent())); //emoji转换
                List<MessageBoard> sonMessageBoardList = messageBoardService.queryMessageBoardByextendsCommentId(messageBoard.getId());
                if (sonMessageBoardList.size() > 0) {
                    List<MessageBoard> messageBoardlist = messageBoard.getList();
                    for (MessageBoard sonMessageBoard : sonMessageBoardList) {
                        sonMessageBoard.setContent(EmojiUtil.emojiConverterUnicodeStr(sonMessageBoard.getContent()));//emoji转换
                        messageBoardlist.add(sonMessageBoard);
                    }
                    querySonMessageBoard(messageBoardlist);
                }
            }
        }
    }



    @PostMapping("/messageboardcommentinsert")
    public String post(MessageBoard messageBoard, HttpServletRequest request){

        String content = HtmlFilter.HTMLTag(messageBoard.getContent());
        String name = HtmlFilter.HTMLTag(messageBoard.getName());
        messageBoard.setName(name);
        messageBoard.setContent(content);

        if (messageBoard.getContent().equals("") || messageBoard.getName().equals("")) {
            return "error/xss";
        }

        int extendsCommentId = messageBoard.getExtendsCommentId();

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser != null) {
            messageBoard.setName(loginUser.getUsername());
            messageBoard.setAvatar(loginUser.getAvatar());
            messageBoard.setAdministrator(true);
        } else {
            messageBoard.setAdministrator(false);
            messageBoard.setAvatar("/image/commentAvatar.jpg");
        }

        if (extendsCommentId != -1) {
            messageBoard.setExtendsCommentId(extendsCommentId);
        } else {
            messageBoard.setExtendsCommentId(0);
        }

        messageBoard.setContent(EmojiUtil.emojiConverterToAlias(messageBoard.getContent()));

        messageBoardService.addMessageBoard(messageBoard);

        return "";
    }


}
