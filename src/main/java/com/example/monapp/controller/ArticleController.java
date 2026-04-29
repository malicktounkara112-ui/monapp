package com.example.monapp.controller;


import com.example.monapp.model.Article;
import com.example.monapp.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Controller qui retourne du JSON
@RequestMapping("/api/articles")  //Prefixe commun à tous les endpoints
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) { this.articleService = articleService; }

    // GET  /api/articles -> tous les articles
    @GetMapping
    public ResponseEntity<List<Article>>  getAllArticles() {
        List<Article>  articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles); // 200 Ok + la liste
    }

    // GET  /api/articles/{id} ->
    @GetMapping("/{id}")
    public ResponseEntity<Article>  getArticleById(@PathVariable Long id){
        return articleService.getArticleById(id)
                .map( article -> ResponseEntity.ok(article))  // 200 si trouvé
                .orElse(ResponseEntity.notFound().build()); // 404 si pas trouvé
    }

    // POST /api/articles -> crée un nouveau article
    @PostMapping
    public ResponseEntity<Article> createArticle(@Valid @RequestBody Article article){
        Article created = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 created
    }
 }
