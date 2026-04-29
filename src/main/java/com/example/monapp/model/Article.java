package com.example.monapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;
@Entity
@Table
public class Article {

    @Id
    @GeneratedValue
    private Long id ;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    public Article() {}

    public Article(Long id, String title, String content){
        this.id=id;
        this.title=title;
        this.content=content;;
    }
    //Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id){ this.id=id; }

    public String getTitle() { return title; }
    public void setTitle(String title){ this.title=title; }

    public String getContent() { return content; }
    public void setContent(String content){ this.content=content; }

}

