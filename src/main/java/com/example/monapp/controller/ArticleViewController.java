package com.example.monapp.controller;

import com.example.monapp.model.Article;
import com.example.monapp.service.ArticleService;
import com.example.monapp.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    // Voir un article
    @GetMapping("/articles/view/{id}")
    public String viewArticle(@PathVariable Long id, Model model) {
        articleService.getArticleById(id).ifPresent(a -> model.addAttribute("article", a));
        return "article-view";
    }

    // Afficher le formulaire de modification
    @GetMapping("/articles/edit/{id}")
    public String editArticleForm(@PathVariable Long id, Model model) {
        articleService.getArticleById(id).ifPresent(a -> model.addAttribute("article", a));
        return "article-edit";
    }

    // Traiter la modification
    @PostMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable Long id,
                              @RequestParam String title,
                              @RequestParam String content) {
        Article article = new Article(null, title, content);
        articleService.updateArticle(id, article);
        return "redirect:/articles";
    }

    // Supprimer un article
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}