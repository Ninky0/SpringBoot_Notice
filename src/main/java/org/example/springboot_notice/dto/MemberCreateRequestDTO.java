package org.example.springboot_notice.dto;

import lombok.Getter;
import org.example.springboot_notice.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class MemberCreateRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String userid;
    private String password;

    public User toUser(BCryptPasswordEncoder bCryptPasswordEncoder){
        return User.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .userid(userid)
                .password(bCryptPasswordEncoder.encode(password))
                .build();
    }
}
