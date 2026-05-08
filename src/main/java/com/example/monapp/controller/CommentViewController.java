package com.example.monapp.controller;

import com.example.monapp.model.Article;
import com.example.monapp.model.Comment;
import com.example.monapp.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentViewController {

    private final CommentService commentService;

    public CommentViewController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String listcomment(Model model) {
        model.addAttribute("comments", commentService.getAllComments());
        return "comments";
    }

    @PostMapping("/comments/add")
    public String addcomment(@RequestParam String text,
                             @RequestParam String author) {
        Comment comment = new Comment(null, text, author);
        commentService.createComment(comment);
        return "redirect:/comments";
    }

    // Voir un article
    @GetMapping("/comments/view/{id}")
    public String viewComment(@PathVariable Long id, Model model) {
        commentService.getCommentById(id).ifPresent(a -> model.addAttribute("comment", a));
        return "comment-view";
    }

    // Afficher le formulaire de modification
    @GetMapping("/comments/edit/{id}")
    public String editCommentForm(@PathVariable Long id, Model model) {
        commentService.getCommentById(id).ifPresent(a -> model.addAttribute("comment", a));
        return "comment-edit";
    }

    // Traiter la modification
    @PostMapping("/comments/edit/{id}")
    public String editComments(@PathVariable Long id,
                               @RequestParam String text,
                               @RequestParam String author) {
        Comment comment = new Comment(null, text, author);
        commentService.updateComment(id, comment);
        return "redirect:/comments";
    }

    // Supprimer un commentaire
    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return "redirect:/comments";
    }
}
