package org.example.springboot_notice.dto.article;

import lombok.Getter;
import org.example.springboot_notice.domain.Article;

import java.util.Date;

@Getter
public class ArticleCreateRequestDTO {
    private String title;
    private String content;
    private String author;
    private Date createTime;

    public Article toArticle(){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .createTime(createTime)
                .build();
    }
}
