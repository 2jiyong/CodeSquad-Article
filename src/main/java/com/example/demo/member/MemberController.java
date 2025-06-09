package com.example.demo.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.member.dto.MemberRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {
  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  @PostMapping
  public ResponseEntity<Void> createMember(@Valid @RequestBody MemberRequest memberRequest) {
    memberService.createMember(memberRequest);
    return ResponseEntity.ok().build();
  }
}
