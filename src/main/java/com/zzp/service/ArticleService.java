package com.zzp.service;

import com.zzp.pojo.Article;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 13:54
 */
public interface ArticleService {

    /**
     * 管理员查看所有文章
     */
    List<Article> adminQueryArticleAll();

    /**
     * 管理员根据类别ID或者文章名称查询
     */
    List<Article> adminQueryArticleByTidAndByTitle(Article article);

    /**
     * 管理员根据ID查询文章
     */
    Article adminQueryArticleById(int id);

    /**
     * 新增博客
     */
    int addArticle(Article article);

    /**
     * 查询全部博客
     */
    List<Article> queryArticleAll();

    /**
     * 根据ID删除博客
     */
    int deleteArticleById(int id);

    /**
     * 根据ID查询博客内容
     */
    Article queryArticleById(int id);

    /**
     * 根据ID修改博客
     */
    int updateArticleById(Article article);

    /**
     * 根据标签ID和文章标题进行模糊查询
     */
    List<Article> queryArticleByTidAndByTitle(Article article);

    /**
     * 根据文章ID进行增加浏览量
     */
    int addArticleViewsById(int id);

    /**
     * 查询博客年份
     */
    List<String> queryArticleYear();

    /**
     * 根据年份查询博客
     */
    List<Article> queryArticleByYear(String year);

    /**
     * 博客评论次数+1
     */
    int addArticleCommentsById(int id);

    /**
     * 博客评论次数-1
     */
    int deleteArticleCommentsById(int id);

    /**
     * 查询文章数量
     */
    int queryArticleCount();

    /**
     * 查询浏览总量
     */
    int queryArticleViewsSum();

    /**
     * 查询评论总量
     */
    int queryArticleCommentSum();
}
