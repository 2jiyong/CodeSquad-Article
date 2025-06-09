package com.example.demo.article.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ArticleListResponse {
  private List<ArticleResponse> articles;
}
