package org.example.springboot_notice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.springboot_notice.dto.ArticleResponseDTO;

import java.util.Date;

@Getter
@Setter
@Builder
public class Article {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private Date updateTime;

    public ArticleResponseDTO toArticleResponseDTO() {
        return ArticleResponseDTO.builder()
                .id(id)
                .title(title)
                .content(content)
                .author(author)
                .createTime(createTime)
                .updateTime(updateTime)
                .build();
    }

}
