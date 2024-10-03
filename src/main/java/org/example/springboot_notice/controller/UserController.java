package org.example.springboot_notice.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.User;
import org.example.springboot_notice.dto.MemberCreateRequestDTO;
import org.example.springboot_notice.dto.MemberDeleteRequestDTO;
import org.example.springboot_notice.dto.MemberResponseDTO;
import org.example.springboot_notice.service.ArticleService;
import org.example.springboot_notice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ArticleService articleService;

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

    @GetMapping("/quit")
    public String deleteForm(HttpSession session, Model model) {
        String userid = (String) session.getAttribute("userid");
        MemberResponseDTO user = userService.findByUserId(userid);
        model.addAttribute("user", user);
        return "user_delete";
    }

    @Transactional
    @DeleteMapping("/quit")
    public ResponseEntity<String> deleteUser(@RequestBody MemberDeleteRequestDTO request, HttpSession session) {
        String userid = (String) session.getAttribute("userid");

        // 사용자 존재 여부 확인
        MemberResponseDTO user = userService.findByUserId(userid);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("사용자 정보가 없습니다.");
        }

        // 비밀번호 검증
        boolean checkValidation = userService.checkValidation(request.getUserid(), request.getPassword());
        if (!checkValidation) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("입력된 정보가 일치하지 않습니다.");
        }

        // 사용자 및 관련 정보 삭제
        articleService.deleteArticle(userid);
        userService.deleteUser(request.toUser());
        session.invalidate();

        return ResponseEntity.ok("탈퇴 완료 및 작성했던 글을 모두 삭제하였습니다.");
    }


}
