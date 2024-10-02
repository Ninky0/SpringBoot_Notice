package org.example.springboot_notice.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.Article;
import org.example.springboot_notice.dto.ArticleResponseDTO;
import org.example.springboot_notice.dto.MemberResponseDTO;
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
}
