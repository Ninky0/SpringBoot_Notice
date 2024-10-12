package org.example.springboot_notice.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.dto.member.MemberLoginResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");

        MemberLoginResponseDTO build = MemberLoginResponseDTO.builder()
                .isLoggedIn(false)
                .message("로그인 실패\n다시 로그인해주세요.")
                .url("/users")
                .build();

        // 디티오(객체)를 통으로 문자열로 만드는 과정 (직렬화)
        response.getWriter().write(objectMapper.writeValueAsString(build));
    }


}
