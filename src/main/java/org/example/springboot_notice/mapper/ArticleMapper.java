package org.example.springboot_notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.springboot_notice.domain.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> findAll();
}
