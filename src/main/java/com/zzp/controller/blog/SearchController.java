package com.zzp.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Article;
import com.zzp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/15 15:11
 */
@Controller
public class SearchController {

    @Autowired
    ArticleService articleService;


    @RequestMapping("/search")
    public String search(@RequestParam("title") String title, Model model){
        model.addAttribute("title", title);
        return "blog/search";
    }


    @RequestMapping("/blogListSearch")
    @ResponseBody
    public Object blogListSearch(@RequestParam("title")String title,
                                 @RequestParam(defaultValue = "1") String pageNum,
                                 @RequestParam(defaultValue = "2") String pageSize){
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        Article article = new Article();
        article.setTitle(title);
        List<Article> articleList = articleService.queryArticleByTidAndByTitle(article);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }


}
