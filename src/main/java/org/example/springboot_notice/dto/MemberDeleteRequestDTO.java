package org.example.springboot_notice.dto;

import lombok.Getter;
import org.example.springboot_notice.domain.User;

@Getter
public class MemberDeleteRequestDTO {
    private Long id;
    private String userid;
    private String password;

    public User toUser(){
        return User.builder()
                .id(id)
                .userid(userid)
                .password(password)
                .build();
    }

}
