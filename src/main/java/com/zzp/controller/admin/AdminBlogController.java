package com.zzp.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzp.pojo.Article;
import com.zzp.pojo.Type;
import com.zzp.pojo.User;
import com.zzp.service.ArticleService;
import com.zzp.service.CommentService;
import com.zzp.service.TypeService;
import com.zzp.service.UserService;
import com.zzp.util.EmojiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 *@Author:zzp
 *@Date:2022/8/13 16:27
 */
@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    @Autowired
    TypeService typeService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/blogs")
    public String adminBlogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 10);
        List<Article> articleList = articleService.adminQueryArticleAll();
        //查询的结果进行分页
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        List<Article> list = pageInfo.getList();

        List<Type> typeList = typeService.queryTypeAll();
        model.addAttribute("tid", 0);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("typeList", typeList);
        model.addAttribute("articleList", list);

        return "admin/blogs";
    }


    @RequestMapping("/blogssearch")
    public String adminBlogsSearch(@RequestParam("title") String title, @RequestParam("tid") int tid, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        Article article = new Article();

        article.setTitle(title);
        article.setTid(tid);

        //设置第几页和每页多少条数据
        PageHelper.startPage(pageNum, 10);

        List<Article> articleList = articleService.adminQueryArticleByTidAndByTitle(article);
        //查询的结果进行分页
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        List<Article> list = pageInfo.getList();

        List<Type> typeList = typeService.queryTypeAll();

        model.addAttribute("title", title);
        model.addAttribute("tid", tid);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("articleList", list);
        model.addAttribute("typeList", typeList);

        return "admin/blogs";
    }


    @RequestMapping("/blogsinsert")
    public String adminBlogsInsert(Model model) {
        List<Type> typeList = typeService.queryTypeAll();
        model.addAttribute("typeList", typeList);
        return "admin/blogs-insert";
    }

    @RequestMapping("/toblogsinsert")
    public String toAdminBlogsInsert(Article article, RedirectAttributes attributes, HttpServletRequest request) {

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        article.setUid(loginUser.getId());

        article.setContent(EmojiUtil.emojiConverterToAlias(article.getContent()));

        int i = articleService.addArticle(article);

        if (i > 0) {
            typeService.addTypeNumberById(article.getTid());
            attributes.addFlashAttribute("msg", "博客添加成功");
            return "redirect:/admin/blogs";
        } else {
            attributes.addFlashAttribute("msg", "博客添加失败");
            return "redirect:/admin/blogs";
        }

//        System.out.println(article);
//        return "redirect:/admin/blogs";

    }

    @RequestMapping("/blogsdelete/{id}")
    public String adminBlogsDelete(@PathVariable("id") int id, RedirectAttributes attributes) {
        Article article = articleService.queryArticleById(id);
        if (article == null) {
            attributes.addFlashAttribute("msg", "博客删除失败");
            return "redirect:/admin/blogs";
        }
        commentService.deleteCommentByAid(id);
        typeService.deleteTypeNumberById(article.getTid());
        articleService.deleteArticleById(id);
        attributes.addFlashAttribute("msg", "博客删除成功");
        return "redirect:/admin/blogs";


    }

    @RequestMapping("/blogsupdate/{id}")
    public String adminBlogsUpdate(@PathVariable("id") int id, Model model) {
        Article article = articleService.adminQueryArticleById(id);
        if (article == null){
            return "error/404";
        }

        article.setContent(EmojiUtil.emojiConverterUnicodeStr(article.getContent()));

        List<Type> typeList = typeService.queryTypeAll();
        model.addAttribute("typeList", typeList);
        model.addAttribute("article", article);
        return "admin/blogs-update";
    }

    @RequestMapping("/toblogsupdate")
    public String toAdminBlogsUpdate(Article article, RedirectAttributes attributes) {
        Date date = new Date();
        article.setUpdateTime(date);
        article.setContent(EmojiUtil.emojiConverterToAlias(article.getContent()));
        int i = articleService.updateArticleById(article);
        if (i > 0) {
            attributes.addFlashAttribute("msg", "ID:" + article.getId() + "的博客内容修改成功");
            return "redirect:/admin/blogs";
        } else {
            attributes.addFlashAttribute("msg", "ID:" + article.getId() + "的博客内容修改失败");
            return "redirect:/admin/blogs";
        }
    }

//  上传图片并回显
    @ResponseBody
    @RequestMapping("/uploadImg")
    public Map<String,Object> uploadImg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        Map<String,Object> map = new HashMap<>();


        try{
            String filename = file.getOriginalFilename(); //获取文件名以及后缀名
            //获取jar包所在目录
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();

            //在jar包所在目录下生成一个upload文件夹用来存储上传的图片
            String dirPath = jarF.getParentFile().toString()+"/upload/";

            UUID uuid = UUID.randomUUID();
            String name = "";
            if (filename != null){
                name = filename.substring(filename.lastIndexOf(".")); //获取文件后缀名
            }

            String fileName = uuid + name;

            File filePath = new File(dirPath);

            if(!filePath.exists()){
                filePath.mkdirs();
            }

            System.out.println(dirPath+fileName);
            //将文件写入磁盘
            file.transferTo(new File(dirPath+fileName));
            //上传成功返回状态信息
            map.put("success",1); //设置回显的数据 0 表示上传失败，1 表示上传成功
            map.put("message","上传成功"); //提示的信息，上传成功或上传失败及错误信息等
            map.put("url","/upload/"+fileName); //图片回显的url 上传成功时才返回
        }catch (Exception e){
            e.printStackTrace();
            //上传失败，返回失败信息
            map.put("success",0); //设置回显的数据 0 表示上传失败，1 表示上传成功
            map.put("message","上传失败"); //提示的信息，上传成功或上传失败及错误信息等
        }


        return map;
    }

}
