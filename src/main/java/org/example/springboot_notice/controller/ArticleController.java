package org.example.springboot_notice.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.domain.Article;
import org.example.springboot_notice.dto.article.ArticleCreateRequestDTO;
import org.example.springboot_notice.dto.article.ArticleDeteleRequestDTO;
import org.example.springboot_notice.dto.article.ArticleResponseDTO;
import org.example.springboot_notice.dto.article.ArticleUpdateRequestDTO;
import org.example.springboot_notice.dto.member.MemberResponseDTO;
import org.example.springboot_notice.service.ArticleService;
import org.example.springboot_notice.service.UserService;
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
    private final UserService userService;

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

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        ArticleResponseDTO article = articleService.findById(id);
        model.addAttribute("article", article);
        return "update_article";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") Long id, @RequestBody ArticleUpdateRequestDTO request) {
        Article article = request.toArticle();
        article.setId(id);
        article.setUpdateTime(new Date());
        articleService.updateArticle(article);
        return ResponseEntity.ok("글 수정 완료");
    }

    @GetMapping("/erase/{id}")
    public String eraseForm(@PathVariable("id") Long id, Model model) {
        ArticleResponseDTO article = articleService.findById(id);
        model.addAttribute("article", article);
        return "erase_article";
    }

    @DeleteMapping("/erase/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id, @RequestBody ArticleDeteleRequestDTO request, HttpSession session) {

        // 사용자 존재 여부 확인
        MemberResponseDTO user = userService.findByUserId(request.getUserid());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("사용자 정보가 없습니다.");
        }

        // 비밀번호 검증[
        boolean checkValidation = userService.checkValidation(request.getUserid(), request.getPassword());
        if (!checkValidation) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"입력된 정보가 일치하지 않습니다.\"}");
        }

        articleService.deleteArticle(id);
        return ResponseEntity.ok("삭제 완료");
    }

    @GetMapping("/search")
    public String searchArticles(@RequestParam String keyword, Model model, RedirectAttributes redirectAttributes) {
        List<ArticleResponseDTO> searchResults = articleService.searchArticle(keyword);
        if (searchResults.isEmpty()) {
            redirectAttributes.addFlashAttribute("noResultsAlert", "검색 결과가 없습니다.");
            return "redirect:/articles";
        }
        model.addAttribute("articles", searchResults);
        return "article_list";
    }
}
