package com.example.demo.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequest {
  @NotEmpty
  private String username;
  @NotEmpty
  private String password;
  @NotEmpty
  private String email;
}
