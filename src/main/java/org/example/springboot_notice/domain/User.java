package org.example.springboot_notice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.springboot_notice.dto.member.MemberResponseDTO;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String userid;
    private String password;

    public MemberResponseDTO toMemberResponseDTO() {
        return MemberResponseDTO.builder()
                .id(id)
                .name(name)
                .email(email)
                .phone(phone)
                .userid(userid)
                .password(password)
                .build();
    }
}
