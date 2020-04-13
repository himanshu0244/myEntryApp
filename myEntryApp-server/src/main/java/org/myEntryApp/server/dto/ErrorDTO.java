package org.myEntryApp.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class ErrorDTO {
  private String errorCode;
  private String errorMessages;
  private String errorCodeUUID;
}
