package org.myEntryApp.server.dto;

import lombok.Data;

@Data
public class BaseResponseDTO<T> {
  private ResponseHeaderDTO responseHeader;

  private T responseBody;
}
