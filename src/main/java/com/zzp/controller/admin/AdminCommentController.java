package com.zzp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Comment;
import com.zzp.service.ArticleService;
import com.zzp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/17 16:13
 */
@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @RequestMapping("/comments")
    public String adminComments(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 10);
        List<Comment> commentList = commentService.queryCommentAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        List<Comment> list = pageInfo.getList();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("commentList",list);
        return "admin/comments";
    }


    @RequestMapping("/commentsdelete/{id}")
    public String adminCommentsDelete(@PathVariable("id") int id, RedirectAttributes attributes){
        Comment comment = commentService.queryCommentById(id);
        if (comment == null){
            attributes.addFlashAttribute("msg","评论删除失败！");
            return "redirect:/admin/comments";
        }
        articleService.deleteArticleCommentsById(comment.getAid());
        commentService.deleteCommentById(id);
        attributes.addFlashAttribute("msg","评论删除成功！");
        return "redirect:/admin/comments";
    }


}
