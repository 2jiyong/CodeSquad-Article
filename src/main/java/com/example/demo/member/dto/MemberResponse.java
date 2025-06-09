package com.example.demo.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {
  private Long id;
  private String username;
  private String email;
}
