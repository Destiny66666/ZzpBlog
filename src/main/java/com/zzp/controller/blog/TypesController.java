package com.zzp.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Article;
import com.zzp.pojo.Type;
import com.zzp.service.ArticleService;
import com.zzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/15 19:49
 */
@Controller
public class TypesController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/types")
    public String types(Model model){
        List<Type> typeList = typeService.queryTypeAll();
        int count = typeService.queryTypeCount();
        model.addAttribute("count", count);
        model.addAttribute("typeList",typeList);
        return "blog/types";
    }

    @RequestMapping("/types/{id}")
    public String typesById(@PathVariable("id") int id, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){

        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 5);

        Article article = new Article();
        article.setTid(id);

        List<Article> articleList = articleService.queryArticleByTidAndByTitle(article);

        Type type = typeService.queryTypeById(id);

        //查询的结果进行分页
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);

        List<Article> list = pageInfo.getList();

        String pd = null;

        for (Article article1 : list) {
            pd += article1;
        }

        if (pd == null && type == null){
            return "error/no-content";
        }

        model.addAttribute("pd",pd);
        model.addAttribute("tid",id);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("articleList",list);

        return "blog/types";
    }

}
