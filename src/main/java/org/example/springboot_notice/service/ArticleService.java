package org.example.springboot_notice.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.Article;
import org.example.springboot_notice.dto.article.ArticleResponseDTO;
import org.example.springboot_notice.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleMapper articleMapper;

    public List<ArticleResponseDTO> findAll(){
        List<Article> articles = articleMapper.findAll();
        return articles.stream()
                .map(Article::toArticleResponseDTO)
                .collect(Collectors.toList());
    }

    public ArticleResponseDTO findById(Long id){
        return articleMapper.findById(id)
                .toArticleResponseDTO();
    }

    public void saveArticle(Article article){
        articleMapper.saveArticle(article);
    }

    public void updateArticle(Article article){
        articleMapper.updateArticle(article);
    }

    public void deleteArticle(Long id){
        articleMapper.deleteArticle(id);
    }

    public void deleteAllArticle(String userid){
        articleMapper.deleteAllArticle(userid);
    }

    public List<ArticleResponseDTO> searchArticle(String keyword){
        List<Article> articles = articleMapper.searchArticle(keyword);
        return articles.stream()
                .map(Article::toArticleResponseDTO)
                .collect(Collectors.toList());
    }
}
