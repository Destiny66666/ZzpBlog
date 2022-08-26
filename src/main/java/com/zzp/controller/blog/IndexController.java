package com.zzp.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Article;
import com.zzp.pojo.Type;
import com.zzp.service.ArticleService;
import com.zzp.service.MessageBoardService;
import com.zzp.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/14 12:52
 */
@Controller
public class IndexController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TypeService typeService;

    @Autowired
    MessageBoardService messageBoardService;

    @RequestMapping({"/","/index"})
    public String index(Model model){

        int commentSum = articleService.queryArticleCommentSum()+messageBoardService.queryMessageBoardCount();
        int viewsSum = articleService.queryArticleViewsSum();
        int articleCount = articleService.queryArticleCount();
        int typeCount = typeService.queryTypeCount();

        model.addAttribute("commentSum",commentSum);
        model.addAttribute("viewsSum",viewsSum);
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("typeCount",typeCount);

        return "blog/index";
    }

    @RequestMapping("/blogList")
    @ResponseBody
    public Object blogList(@RequestParam(defaultValue = "1") String pageNum,@RequestParam(defaultValue = "5") String pageSize){
        //设置第几页和每页多少条数据
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Article> articleList = articleService.queryArticleAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }


    @RequestMapping("/typeList")
    @ResponseBody
    public Object typeList(){
        List<Type> typeList = typeService.queryTypeAll();
        return typeList;
    }

}
