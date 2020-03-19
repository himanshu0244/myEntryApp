package org.myEntryApp.server.exception;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppRuntimeException extends RuntimeException implements Serializable {
  private static final long serialVersionUID = -3094483518571578837L;
  private List<String> errorCodes;
  private Class objectType;
  private String errorCode;
  private String message;
  private UUID errorUUID;

  public AppRuntimeException(String errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
    this.errorUUID = UUID.randomUUID();
  }

  public AppRuntimeException(String errorCode, String message, Class objectType) {
    this(errorCode, message);
    this.setObjectType(objectType);
  }

  public String getMessage() {
    return this.message;
  }
}
