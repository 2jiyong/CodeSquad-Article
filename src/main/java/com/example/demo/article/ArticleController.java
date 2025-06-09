package com.example.demo.article;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.article.dto.ArticleListResponse;
import com.example.demo.article.dto.ArticleRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping
  public ResponseEntity<ArticleListResponse> getAllArticles() {
    ArticleListResponse articleListResponse = articleService.getAllArticles();
    return ResponseEntity.ok(articleListResponse);
  }

  @PostMapping
  public ResponseEntity<Void> addArticle(@Valid @RequestBody ArticleRequest articleRequest) {
    articleService.addArticle(articleRequest);
    return ResponseEntity.status(201).build();
  }
}
