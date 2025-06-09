package com.example.demo.common.excepition;

public class UnauthorizedException extends BaseException {
  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UnauthorizedException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }
}
