package com.example.demo.article;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.article.dto.ArticleListResponse;
import com.example.demo.article.dto.ArticleRequest;
import com.example.demo.comment.CommentService;
import com.example.demo.comment.dto.CommentRequest;
import com.example.demo.common.auth.AuthUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleService articleService;
  private final CommentService commentService;

  public ArticleController(ArticleService articleService, CommentService commentService) {
    this.articleService = articleService;
    this.commentService = commentService;
  }

  @GetMapping
  public ResponseEntity<ArticleListResponse> getAllArticles() {
    ArticleListResponse articleListResponse = articleService.getAllArticles();
    return ResponseEntity.ok(articleListResponse);
  }

  @PostMapping
  public ResponseEntity<Void> addArticle(@Valid @RequestBody ArticleRequest articleRequest,
      HttpServletRequest request) {
    Long userId = AuthUtils.extractUserId(request);
    articleService.addArticle(articleRequest, userId);
    return ResponseEntity.status(201).build();
  }

  @PostMapping("/{id}/comments")
  public ResponseEntity<Void> addComment(@Valid @RequestBody CommentRequest commentRequest,
      @PathVariable Long id, HttpServletRequest request) {
    Long userId = AuthUtils.extractUserId(request);
    commentService.addComment(commentRequest, id, userId);
    return ResponseEntity.status(201).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable Long id, HttpServletRequest request) {
    Long userId = AuthUtils.extractUserId(request);
    articleService.deleteArticle(id, userId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateArticle(@PathVariable Long id,
      @Valid @RequestBody ArticleRequest articleRequest, HttpServletRequest request) {
    Long userId = AuthUtils.extractUserId(request);
    articleService.updateArticle(articleRequest, id, userId);
    return ResponseEntity.noContent().build();
  }
}
