package com.example.demo.article.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleRequest {
  @NotEmpty
  private String title;
  @NotEmpty
  private String content;
}
