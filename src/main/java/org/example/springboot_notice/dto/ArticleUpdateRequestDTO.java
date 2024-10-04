package org.example.springboot_notice.dto;

import lombok.Getter;
import org.example.springboot_notice.domain.Article;

import java.util.Date;

@Getter
public class ArticleUpdateRequestDTO {
    private String title;
    private String content;
    private String author;
    private Date updateTime;

    public Article toArticle(){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .updateTime(updateTime)
                .build();
    }
}
