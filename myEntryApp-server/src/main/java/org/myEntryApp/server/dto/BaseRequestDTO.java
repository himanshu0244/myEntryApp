package org.myEntryApp.server.dto;

import lombok.Data;
@Data
public class BaseRequestDTO<T> {

private RequestHeaderDTO requestHeader;

private T requestBody;
}
