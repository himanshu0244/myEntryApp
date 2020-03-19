package org.myEntryApp.server.utils;

import java.util.Date;
import java.util.UUID;

import org.myEntryApp.server.dto.ErrorDTO;
import org.myEntryApp.server.dto.GeneralErrorEnum;
import org.myEntryApp.server.dto.ResponseHeaderDTO;
import org.myEntryApp.server.exception.AppRuntimeException;

public class ApplicationUtils {
  private ApplicationUtils() {

  }

  /**
   * This method prepare the response object with given exception/error details
   * 
   * @param startTime
   * @param ex
   * @return
   */
  public static ResponseHeaderDTO prepareResponseExceptionObject(long startTime, Exception ex) {
    long endTime = System.currentTimeMillis();
    String uuid = UUID.randomUUID().toString();
    ResponseHeaderDTO responseHeaderDTO = new ResponseHeaderDTO();
    responseHeaderDTO.setResponseStatus(GeneralErrorEnum.FAILED.getErrorMessage());
    responseHeaderDTO
      .setTransactionId(uuid);

    responseHeaderDTO.setResponseTime(String.valueOf(endTime - startTime));
    responseHeaderDTO.setResponseTimeStamp(new Date());

    ErrorDTO errorDTO = new ErrorDTO();
    if (ex instanceof AppRuntimeException) {
      errorDTO.setErrorCode(((AppRuntimeException) ex).getErrorCode());
      errorDTO.setErrorMessages(((AppRuntimeException) ex).getMessage());
    } else {
      errorDTO.setErrorCode(GeneralErrorEnum.UNKNOWN.getErrorCode());
      errorDTO.setErrorMessages(GeneralErrorEnum.UNKNOWN.getErrorMessage());
    }
    errorDTO.setErrorCodeUUID(uuid);

    responseHeaderDTO.setError(errorDTO);
    return responseHeaderDTO;
  }

  /**
   * This method prepare the response object with given exception/error details
   * 
   * @param ex
   * @return
   */
  public static ResponseHeaderDTO prepareResponseExceptionObject(Exception ex) {

    ResponseHeaderDTO responseHeaderDTO = prepareResponseHeaderObject(GeneralErrorEnum.FAILED, null);

    ErrorDTO errorDTO = new ErrorDTO();
    if (ex instanceof AppRuntimeException) {
      errorDTO.setErrorCode(((AppRuntimeException) ex).getErrorCode());
      errorDTO.setErrorMessages(((AppRuntimeException) ex).getMessage());

    } else {

      errorDTO.setErrorCode(GeneralErrorEnum.UNKNOWN.getErrorCode());
      errorDTO.setErrorMessages(ex.getMessage());
    }
    errorDTO.setErrorCodeUUID(UUID.randomUUID().toString());

    responseHeaderDTO.setError(errorDTO);
    return responseHeaderDTO;
  }

  /**
   * This method prepare the success response object
   * 
   * @param startTime
   * @return
   */
  public static ResponseHeaderDTO prepareResponseHeader(long startTime) {
    long endTime = System.currentTimeMillis();
    return prepareResponseHeaderObject(GeneralErrorEnum.SUCCESS, String.valueOf(endTime - startTime));
  }

  private static ResponseHeaderDTO prepareResponseHeaderObject(GeneralErrorEnum responseStatus, String responseTime) {
      ResponseHeaderDTO responseHeaderDTO = new ResponseHeaderDTO();
      responseHeaderDTO.setResponseStatus(responseStatus.getErrorMessage());
      responseHeaderDTO
          .setTransactionId(UUID.randomUUID().toString());
      responseHeaderDTO.setResponseCode(responseStatus.getCode());
      if (null != responseTime) {
          responseHeaderDTO.setResponseTime(responseTime);
      }
      responseHeaderDTO.setResponseTimeStamp(new Date());
      return responseHeaderDTO;
  }
}
