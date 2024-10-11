package org.example.springboot_notice.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.User;
import org.example.springboot_notice.dto.SignInResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        User user = userDetails.getUser();

        //세션 설정
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserid());
        session.setAttribute("userName", user.getName());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        SignInResponseDTO build = SignInResponseDTO.builder()
                .isLoggedIn(true)
                .message("로그인 성공")
                .url("/articles")
                .userId(user.getUserid())
                .userName(user.getName())
                .build();

        response.getWriter().write(
                objectMapper.writeValueAsString(build)
        );
    }

}
