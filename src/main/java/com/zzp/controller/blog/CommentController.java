package com.zzp.controller.blog;

import com.zzp.pojo.Comment;
import com.zzp.pojo.User;
import com.zzp.service.ArticleService;
import com.zzp.service.CommentService;
import com.zzp.service.UserService;
import com.zzp.util.EmojiUtil;
import com.zzp.util.HtmlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/17 13:06
 */

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;


    @GetMapping("/comments")
    @ResponseBody
    public Object comments(int blogId) {
        List<Comment> comments = commentService.queryCommentByAidAndExtendsCommentIdIsZero(blogId);
        querySonComment(comments);
        return comments;
    }


    //递归查询子评论
    public void querySonComment(List<Comment> list) {
        if (list.size() > 0) {
            for (Comment comment : list) {
                comment.setContent(EmojiUtil.emojiConverterUnicodeStr(comment.getContent())); //emoji转换
                List<Comment> sonCommenList = commentService.queryCommentByExtendsCommentId(comment.getId());
                if (sonCommenList.size() > 0) {
                    List<Comment> Commentlist = comment.getList();
                    for (Comment soncomment : sonCommenList) {
                        soncomment.setContent(EmojiUtil.emojiConverterUnicodeStr(soncomment.getContent())); //emoji转换
                        Commentlist.add(soncomment);
                    }
                    querySonComment(Commentlist);
                }
            }
        }
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpServletRequest request) {

        String content = HtmlFilter.HTMLTag(comment.getContent());
        String name = HtmlFilter.HTMLTag(comment.getName());
        comment.setName(name);
        comment.setContent(content);

        if (comment.getContent().equals("") || comment.getName().equals("")) {
            return "error/xss";
        }

        int extendsCommentId = comment.getExtendsCommentId();

        User loginUser = (User) request.getSession().getAttribute("loginUser");


        if (loginUser != null) {
            comment.setName(loginUser.getUsername());
            comment.setAvatar(loginUser.getAvatar());
            comment.setAdministrator(true);
        } else {
            comment.setAdministrator(false);
            comment.setAvatar("/image/commentAvatar.jpg");
        }

        if (extendsCommentId != -1) {
            comment.setExtendsCommentId(extendsCommentId);
        } else {
            comment.setExtendsCommentId(0);
        }

        comment.setContent(EmojiUtil.emojiConverterToAlias(comment.getContent()));

        commentService.addComment(comment);
        articleService.addArticleCommentsById(comment.getAid());

        return "redirect:/comments/" + comment.getAid();
    }


}
