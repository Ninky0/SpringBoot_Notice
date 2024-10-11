package org.example.springboot_notice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponseDTO {
    private boolean isLoggedIn;
    private String url;
    private String userName;
    private String userId;
    private String message;
}