package org.example.springboot_notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private Date updateTime;
}
