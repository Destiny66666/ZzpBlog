package com.zzp.controller.blog;

import com.zzp.pojo.Article;
import com.zzp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author:zzp
 *@Date:2022/8/18 13:10
 */
@Controller
public class ArchivesController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/archives")
    public String archives(Model model){
        List<String> years = articleService.queryArticleYear();
        Map<String, List<Article>> map = new HashMap<>();
        for(String year : years){
            List<Article> articleList = articleService.queryArticleByYear(year);
            map.put(year, articleList);
        }
        model.addAttribute("articleMap",map);
        return "blog/archives";
    }

}
