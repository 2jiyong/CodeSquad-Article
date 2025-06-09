package com.example.demo.article.dto;

import java.time.LocalDateTime;

import com.example.demo.member.dto.MemberResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleDetailResponse {
  private Long id;
  private String title;
  private String content;
  private MemberResponse memberResponse;
  private LocalDateTime createdAt;
}
