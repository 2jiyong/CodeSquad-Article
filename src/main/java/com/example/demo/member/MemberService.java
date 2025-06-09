package com.example.demo.member;

import org.springframework.stereotype.Service;
import com.example.demo.common.auth.dto.SignupRequest;

@Service
public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
}
