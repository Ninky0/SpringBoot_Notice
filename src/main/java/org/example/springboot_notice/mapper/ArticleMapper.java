package org.example.springboot_notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboot_notice.domain.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> findAll();
    Article findById(Long id);
    void saveArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(Long id);
    void deleteAllArticle(String userid);
    List<Article> searchArticle(String keyword);
}
