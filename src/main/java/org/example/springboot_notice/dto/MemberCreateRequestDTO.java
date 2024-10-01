package org.example.springboot_notice.dto;

import lombok.Getter;
import org.example.springboot_notice.domain.User;

@Getter
public class MemberCreateRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String userid;
    private String password;

    public User toUser(){
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .userid(userid)
                .password(password)
                .build();
    }
}
