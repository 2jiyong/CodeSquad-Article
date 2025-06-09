package com.example.demo.member;

import org.springframework.stereotype.Service;
import com.example.demo.member.dto.MemberRequest;

@Service
public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void createMember(MemberRequest memberRequest) {
    memberRepository.save(Member.makeMember(memberRequest));
  }
}
