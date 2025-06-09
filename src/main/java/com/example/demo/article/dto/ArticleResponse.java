package com.example.demo.article.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleResponse {
  private Long id;
  private String title;
  private String content;
  private String authorName;
  private LocalDateTime createdAt;
}
