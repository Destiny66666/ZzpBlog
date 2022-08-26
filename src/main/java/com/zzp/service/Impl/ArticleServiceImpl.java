package com.zzp.service.Impl;

import com.zzp.mapper.ArticleMapper;
import com.zzp.pojo.Article;
import com.zzp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author:zzp
 *@Date:2022/8/13 14:05
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;


    @Override
    public List<Article> adminQueryArticleAll() {
        return articleMapper.adminQueryArticleAll();
    }

    @Override
    public List<Article> adminQueryArticleByTidAndByTitle(Article article) {
        return articleMapper.adminQueryArticleByTidAndByTitle(article);
    }

    @Override
    public Article adminQueryArticleById(int id) {
        return articleMapper.adminQueryArticleById(id);
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public List<Article> queryArticleAll() {
        return articleMapper.queryArticleAll();
    }

    @Override
    public int deleteArticleById(int id) {
        return articleMapper.deleteArticleById(id);
    }

    @Override
    public Article queryArticleById(int id) {
        return articleMapper.queryArticleById(id);
    }

    @Override
    public int updateArticleById(Article article) {
        return articleMapper.updateArticleById(article);
    }

    @Override
    public List<Article> queryArticleByTidAndByTitle(Article article) {
        return articleMapper.queryArticleByTidAndByTitle(article);
    }

    @Override
    public int addArticleViewsById(int id) {
        return articleMapper.addArticleViewsById(id);
    }

    @Override
    public List<String> queryArticleYear() {
        return articleMapper.queryArticleYear();
    }

    @Override
    public List<Article> queryArticleByYear(String year) {
        return articleMapper.queryArticleByYear(year);
    }

    @Override
    public int addArticleCommentsById(int id) {
        return articleMapper.addArticleCommentsById(id);
    }

    @Override
    public int deleteArticleCommentsById(int id) {
        return articleMapper.deleteArticleCommentsById(id);
    }

    @Override
    public int queryArticleCount() {
        return articleMapper.queryArticleCount();
    }

    @Override
    public int queryArticleViewsSum() {
        return articleMapper.queryArticleViewsSum();
    }

    @Override
    public int queryArticleCommentSum() {
        return articleMapper.queryArticleCommentSum();
    }


}
