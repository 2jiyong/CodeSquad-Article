package com.example.demo.common.auth;

import com.example.demo.common.excepition.ErrorCode;
import com.example.demo.common.excepition.UnauthorizedException;

import jakarta.servlet.http.HttpServletRequest;

public class AuthUtils {

  public static Long extractUserId(HttpServletRequest request) {
    Object userId = request.getAttribute("jwt.userId");
    if (userId == null) {
      throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
    }
    return (Long) userId;
  }
}

