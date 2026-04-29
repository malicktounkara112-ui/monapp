package com.example.monapp.service;

import com.example.monapp.model.Article;
import com.example.monapp.model.Comment;
import com.example.monapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){this.commentRepository=commentRepository;}

    // récupérer tous les commentaires
    public List<Comment> getAllComments() {return commentRepository.findAll();}

    //récupérer un commentaires par  ID
    public Optional<Comment> getCommentById(Long id){return commentRepository.findById(id);}

    //créer commentaire
    public Comment createComment(Comment comment){return commentRepository.save(comment);}

}
