package com.example.demo.common.auth;

import org.springframework.stereotype.Service;

import com.example.demo.common.auth.dto.LoginRequest;
import com.example.demo.common.auth.dto.LoginResponse;
import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.NotFoundException;
import com.example.demo.common.excepition.UnauthorizedException;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.common.auth.dto.SignupRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtTokenProvider jwtTokenProvider;
  private final MemberRepository memberRepository;

  public LoginResponse login(LoginRequest loginRequest) {
    Member Member = memberRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

    if (!Member.getPassword().equals(loginRequest.getPassword())) {
      throw new UnauthorizedException(ErrorCode.INVALID_PASSWORD);
    }

    String accessToken = jwtTokenProvider.createToken(Member.getId(), Member.getUsername());

    return new LoginResponse(accessToken);
  }

  public void signup(SignupRequest signupRequest) {
    if (memberRepository.existsByEmail(signupRequest.getEmail())) {
      throw new UnauthorizedException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }

    if (memberRepository.existsByUsername(signupRequest.getUsername())) {
      throw new UnauthorizedException(ErrorCode.USERNAME_ALREADY_EXISTS);
    }

    Member member = Member.makeMember(signupRequest);

    memberRepository.save(member);
  }
}
