package com.example.demo.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.article.dto.ArticleDetailResponse;
import com.example.demo.article.dto.ArticleListResponse;
import com.example.demo.article.dto.ArticleRequest;
import com.example.demo.article.dto.ArticleResponse;
import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.NotFoundException;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.dto.MemberResponse;

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

  @Transactional(readOnly = true)
  public Page<Article> getAllArticles(Pageable pageable) {
    return articleRepository.findAll(pageable);
  }

    @Transactional
  public void deleteArticle(Long id, Long userId) {
    Article article = articleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND));

    if (!article.getAuthor().getId().equals(userId)) {
      throw new NotFoundException(ErrorCode.UNAUTHORIZED);
    }

    articleRepository.delete(article);
  }

  @Transactional
  public void updateArticle(ArticleRequest request, Long id, Long userId) {
    Article article = articleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND));

    if (!article.getAuthor().getId().equals(userId)) {
      throw new NotFoundException(ErrorCode.UNAUTHORIZED);
    }

    article.update(request.getTitle(), request.getContent());
  }

  @Transactional(readOnly = true)
  public ArticleDetailResponse findArticleById(Long id) {
    Article article = articleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
    Member author = article.getAuthor();
    MemberResponse memberResponse = MemberResponse.builder()
        .id(author.getId())
        .username(author.getUsername())
        .email(author.getEmail())
        .build();

    return ArticleDetailResponse.builder()
        .id(article.getId())
        .title(article.getTitle())
        .content(article.getContent())
        .memberResponse(memberResponse)
        .createdAt(article.getCreatedAt())
        .build();
  }
}
