package org.example.springboot_notice.dto;

import lombok.Getter;
import org.example.springboot_notice.domain.User;

@Getter
public class ArticleDeteleRequestDTO {
    private String userid;
    private String password;

    public User toUser(){
        return User.builder()
                .userid(userid)
                .password(password)
                .build();
    }
}
