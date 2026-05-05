package com.example.monapp.controller;

import com.example.monapp.model.Comment;
import com.example.monapp.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            Comment comment = new Comment(null,text, author);
            commentService.createComment(comment);
            return "redirect:/comments";
        }
    }
