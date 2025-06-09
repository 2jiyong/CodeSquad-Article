package com.example.demo.article;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.article.dto.ArticleListResponse;
import com.example.demo.article.dto.ArticleRequest;
import com.example.demo.article.dto.ArticleResponse;
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

  private static final Member DEFAULT_MEMBER = Member.builder()
  .id(1L)
  .username("defaultUser")
  .password("defaultPassword")
  .build();

  public void addArticle(ArticleRequest request) {
    Article article = Article.builder()
        .title(request.getTitle())
        .content(request.getContent())
        .author(DEFAULT_MEMBER) //TODO: 로그인 기능 구현 후 수정
        .build();

    articleRepository.save(article);
  }
}
