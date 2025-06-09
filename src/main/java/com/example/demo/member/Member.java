package com.example.demo.member;

import com.example.demo.member.dto.MemberRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;
  @Column(nullable = false, unique = true)
  private String password;
  @Column(nullable = false, unique = true)
  private String email;

  public static Member makeMember(MemberRequest memberRequest) {
    return Member.builder()
        .username(memberRequest.getUsername())
        .password(memberRequest.getPassword())
        .email(memberRequest.getEmail())
        .build();
  }
}
