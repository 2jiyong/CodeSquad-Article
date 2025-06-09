package com.example.demo.common.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.common.auth.dto.LoginRequest;
import com.example.demo.common.auth.dto.LoginResponse;
import com.example.demo.common.auth.dto.SignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
      @Valid @RequestBody LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<Void> signup(
      @Valid @RequestBody SignupRequest request) {
    authService.signup(request);
    return ResponseEntity.ok().build();
  }
}
