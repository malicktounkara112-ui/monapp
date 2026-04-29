package com.example.monapp.controller;


import com.example.monapp.model.Article;
import com.example.monapp.model.Comment;
import com.example.monapp.service.ArticleService;
import com.example.monapp.service.CommentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller qui retourne du JSON
@RequestMapping("/api/articles/{articleId}/comments") // Préfixe commun à tous les endpoints
public class CommentController {

    private final CommentService commentService;
    private final ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    // GET /api/comment -> tous les commentaires
    @GetMapping
    public ResponseEntity<List<Comment>>  getAllComments() {
        List<Comment>  comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);  // 200 Ok + la liste
    }

    // GET /api/comment{id} ->
    @GetMapping("/{id}")
    public ResponseEntity<Comment>   getCommentById(@PathVariable Long articleId){
        return commentService.getCommentById(articleId)
                .map(comment -> ResponseEntity.ok(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/comments -> crée un nouveau commentaire
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @PathVariable Long articleId,
            @Valid @RequestBody Comment comment) {

        // 1. Trouver l'article
        Article article = articleService.getArticleById(articleId)
                .orElse(null);

        // 2. Si article pas trouvé → 404
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        // 3. Lier le commentaire à l'article
        comment.setArticle(article);

        // 4. Sauvegarder et retourner
        Comment created = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
