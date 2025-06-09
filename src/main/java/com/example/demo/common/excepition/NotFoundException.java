package com.example.demo.common.excepition;

public class NotFoundException extends BaseException {
  public NotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public NotFoundException(ErrorCode errorCode, String customMessage) {
    super(errorCode, customMessage);
  }
}
