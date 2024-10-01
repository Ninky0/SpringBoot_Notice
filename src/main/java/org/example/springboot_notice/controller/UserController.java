package org.example.springboot_notice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.dto.MemberCreateRequestDTO;
import org.example.springboot_notice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String registerForm() {
        return "sign_log";
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody MemberCreateRequestDTO request) {
        boolean isMember = userService.joinedUser(request.toUser());
        if (isMember) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입한 사용자입니다.");
        }

        boolean isUsing = userService.validId(request.toUser());
        if (!isUsing) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용중인 ID입니다.");
        }

        userService.createUser(request.toUser());
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberCreateRequestDTO request) {

        return "redirect:/articles";
    }

}
