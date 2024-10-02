package org.example.springboot_notice.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.User;
import org.example.springboot_notice.dto.MemberCreateRequestDTO;
import org.example.springboot_notice.dto.MemberLoginRequestDTO;
import org.example.springboot_notice.dto.MemberResponseDTO;
import org.example.springboot_notice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class SessionController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestBody MemberLoginRequestDTO request) {
        User user = request.toUser();
        String userid = user.getUserid();
        String password = user.getPassword();

        // 유저 서비스에서 유저 정보 조회
        MemberResponseDTO responseDTO = userService.findByUserId(userid);

        // 비밀번호 검증
        String rightPassword = responseDTO.getPassword();

        // 비밀번호가 일치하면 세션 설정 및 성공 응답
        if (rightPassword.equals(password)) {
            session.setAttribute("userid", userid);
            // 성공 응답, 리다이렉트 URL 포함
            return ResponseEntity.ok(Map.of("success", true, "redirectUrl", "/articles"));
        }
        // 비밀번호 불일치, 실패 응답
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid credentials"));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션의 모든 속성 제거
        // 로그아웃 성공 응답, 리다이렉트 URL 포함
        return ResponseEntity.ok(Map.of("success", true, "redirectUrl", "/users"));
    }
}
