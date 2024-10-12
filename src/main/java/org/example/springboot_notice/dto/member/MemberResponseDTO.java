package org.example.springboot_notice.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String userid;
    private String password;
}
