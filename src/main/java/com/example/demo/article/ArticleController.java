package com.example.demo.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.article.dto.ArticleDetailResponse;
import com.example.demo.article.dto.ArticleRequest;
import com.example.demo.comment.Comment;
import com.example.demo.comment.CommentService;
import com.example.demo.comment.dto.CommentRequest;
import com.example.demo.common.auth.AuthUtils;
import com.example.demo.image.ImageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

  private final ArticleService articleService;
  private final CommentService commentService;
  private final ImageService imageService;

  public ArticleController(ArticleService articleService, CommentService commentService,
      ImageService imageService) {
    this.articleService = articleService;
    this.commentService = commentService;
    this.imageService = imageService;
  }

  @GetMapping
  public ResponseEntity<Page<Article>> getAllArticles(Pageable pageable) {
    return ResponseEntity.ok(articleService.getAllArticles(pageable));
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

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDetailResponse> getArticleById(@PathVariable Long id) {
    ArticleDetailResponse articleDetailResponse = articleService.findArticleById(id);
    return ResponseEntity.ok(articleDetailResponse);
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

  @GetMapping("/{id}/comments")
  public ResponseEntity<Slice<Comment>> getArticleDetail(@PathVariable Long id,
      @RequestParam(required = false, defaultValue = "10") int size,
      @RequestParam(name = "lastItemId", required = false) Long lastItemId) {
    Slice<Comment> comments = commentService.getCommentsByArticleId(id, size, lastItemId);
    return ResponseEntity.ok(comments);
  }

  @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Void> uploadImage(@PathVariable Long id,
      @RequestPart("image") MultipartFile image, HttpServletRequest request) {
    Long userId = AuthUtils.extractUserId(request);
    imageService.uploadImage(id, image, userId);
    return ResponseEntity.status(201).build();
  }
}
