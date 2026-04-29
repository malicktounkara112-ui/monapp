package com.example.monapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public  class Comment {
    @Id
    @GeneratedValue

    private Long id;

    @NotNull
    private String text;

    @NotNull
    private String author;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment() {}

    public Comment(Long id, String text, String author){
        this.id=id;
        this.text=text;
        this.author=author;
    }
//getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getText(){return text;}
    public void setText(String text){this.text=text;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author=author;}

    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}
