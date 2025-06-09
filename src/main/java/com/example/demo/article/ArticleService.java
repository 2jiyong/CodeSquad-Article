package com.example.demo.article;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.article.dto.ArticleListResponse;
import com.example.demo.article.dto.ArticleRequest;
import com.example.demo.article.dto.ArticleResponse;
import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.NotFoundException;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;

@Service
public class ArticleService {
  private final ArticleRepository articleRepository;
  private final MemberRepository memberRepository;

  public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
    this.articleRepository = articleRepository;
    this.memberRepository = memberRepository;
  }

  @Transactional
  public void addArticle(ArticleRequest request, Long userId) {
    Article article = Article.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .author(memberRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(
            ErrorCode.USER_NOT_FOUND)))
        .build();

    articleRepository.save(article);
  }

  @Transactional(readOnly = true)
  public ArticleListResponse getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    List<ArticleResponse> articleResponses = articles.stream()
        .map(a -> ArticleResponse.builder()
            .id(a.getId())
            .title(a.getTitle())
            .content(a.getContent())
            .authorName(a.getAuthor().getUsername())
            .createdAt(a.getCreatedAt())
            .build())
        .toList();

    return ArticleListResponse.builder()
        .articles(articleResponses)
        .build();
  }
}
