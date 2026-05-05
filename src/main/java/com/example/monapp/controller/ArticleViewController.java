package com.example.monapp.controller;

import com.example.monapp.model.Article;
import com.example.monapp.service.ArticleService;
import com.example.monapp.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleViewController {

    private final ArticleService articleService;

    public ArticleViewController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String listarticles(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles";
    }
    @PostMapping("/articles/add")
    public String addarticle(@RequestParam String title,
                             @RequestParam String content) {
        Article article = new Article(null,title, content);
        articleService.createArticle(article);
        return "redirect:/articles";
    }
}