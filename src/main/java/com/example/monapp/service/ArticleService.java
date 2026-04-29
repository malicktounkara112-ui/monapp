package com.example.monapp.service;

import com.example.monapp.model.Article;
import com.example.monapp.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }

    // récupérer tous les articles
    public List<Article> getAllArticles() {return articleRepository.findAll(); }

    //Récupérer un article par Id
    public Optional<Article> getArticleById(Long id){return articleRepository.findById(id);}

    // créer Article
    public Article createArticle(Article article){return articleRepository.save(article);}

}
