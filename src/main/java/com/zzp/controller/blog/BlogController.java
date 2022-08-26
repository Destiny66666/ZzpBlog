package com.zzp.controller.blog;

import com.zzp.pojo.Article;
import com.zzp.pojo.User;
import com.zzp.service.ArticleService;
import com.zzp.service.CommentService;
import com.zzp.util.EmojiUtil;
import com.zzp.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author:zzp
 *@Date:2022/8/14 15:33
 */
@Controller
public class BlogController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable("id") int id, Model model, HttpServletRequest request){

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser != null){
            model.addAttribute("loginUser",loginUser);
        }

        Article article = articleService.queryArticleById(id);
        if (article == null){
            return "error/no-content";
        }
        Article article1 = new Article();

        BeanUtils.copyProperties(article, article1);

        String content = article1.getContent();

        article1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        String content1 = article1.getContent();
        article1.setContent(EmojiUtil.emojiConverterUnicodeStr(content1));
        model.addAttribute("article",article1);

        return "blog/blog";
    }


    @RequestMapping("/addblogviews")
    @ResponseBody
    public String addBlogViews(@RequestParam("id") String id){
        int i = Integer.parseInt(id);
        articleService.addArticleViewsById(i);
        return null;
    }

}
