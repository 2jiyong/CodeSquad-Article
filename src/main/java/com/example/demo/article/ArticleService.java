package com.example.demo.article;

import org.springframework.stereotype.Service;
import com.example.demo.article.dto.ArticleRequest;

@Service
public class ArticleService {
  private final ArticleRepository articleRepository;

  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  public void addArticle(ArticleRequest request) {
    Article article = Article.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .authorId(1L) //TODO: 로그인 기능 구현 후 수정
        .build();

    articleRepository.save(article);
  }
}
