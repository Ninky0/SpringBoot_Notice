package org.example.springboot_notice.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDTO {
    private boolean isLoggedIn;
    private String url;
    private String userName;
    private String userId;
    private String message;
}