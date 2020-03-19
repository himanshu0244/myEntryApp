package org.myEntryApp.server.dto;

public enum GeneralErrorEnum {
  
  SUCCESS(100, "SUCCESS", "Success"), FAILED(101, "FAILED", "Failed"),
  UNKNOWN(0, "UNKNOWN", "Unknown");

  private int code;
  private String errorCode;
  private String errorMessage;

  GeneralErrorEnum(int code, String errorCode, String errorMessage) {
    this.code = code;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public int getCode() {
    return code;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}
