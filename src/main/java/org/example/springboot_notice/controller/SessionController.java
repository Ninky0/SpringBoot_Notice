package org.example.springboot_notice.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class SessionController {
    private final UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(HttpSession session, @RequestBody MemberLoginRequestDTO request) {
//        User user = request.toUser();
//        String userid = user.getUserid();
//        String password = user.getPassword();
//
//        // 유저 서비스에서 유저 정보 조회
//        MemberResponseDTO responseDTO = userService.findByUserId(userid);
//
//        // 비밀번호 검증
//        String rightPassword = responseDTO.getPassword();
//
//        // 비밀번호가 일치하면 세션 설정 및 성공 응답
//        if (rightPassword.equals(password)) {
//            session.setAttribute("userid", userid);
//            // 성공 응답, 리다이렉트 URL 포함
//            return ResponseEntity.ok(Map.of("success", true, "redirectUrl", "/articles"));
//        }
//        // 비밀번호 불일치, 실패 응답
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid credentials"));
//    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/users"); // 직접적인 리다이렉트
    }
}
