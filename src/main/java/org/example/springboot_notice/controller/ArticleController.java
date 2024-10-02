package org.example.springboot_notice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.dto.ArticleResponseDTO;
import org.example.springboot_notice.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String registerForm(Model model) {
        return "create_article";
    }

    @GetMapping("/detail/{id}")
    public String viewDetailById(@PathVariable("id") Long id, Model model) {
        ArticleResponseDTO article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

}
