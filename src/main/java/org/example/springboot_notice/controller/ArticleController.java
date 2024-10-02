package org.example.springboot_notice.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.Article;
import org.example.springboot_notice.dto.ArticleCreateRequestDTO;
import org.example.springboot_notice.dto.ArticleResponseDTO;
import org.example.springboot_notice.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public String findAllArticle(Model model) {
        List<ArticleResponseDTO> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "article_list";
    }

    @GetMapping("/register")
    public String registerForm(HttpSession session, RedirectAttributes redirectAttributes) {
        String userid = (String) session.getAttribute("userid");
        System.out.println(userid);
        if (userid == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
            return "redirect:/users";
        }
        return "create_article";
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveArticle(@RequestBody ArticleCreateRequestDTO request, HttpSession session) {
        Article article = request.toArticle();

        String userid = (String) session.getAttribute("userid");

        // System.out.println(userid);

        article.setAuthor(userid); // Article 객체에 userid 설정
        article.setCreateTime(new Date()); // 생성 시간 설정

        articleService.saveArticle(article);

        return ResponseEntity.ok("글이 성공적으로 등록되었습니다.");
    }

    @GetMapping("/detail/{id}")
    public String viewDetailById(@PathVariable("id") Long id, Model model) {
        ArticleResponseDTO article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

}
