///*
// * Account.java
// */
//package org.myEntryApp.server.domain;
//
//import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT;
//import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
//import static org.myEntryApp.server.constants.CommonConstants.ID;
//import static org.myEntryApp.server.constants.CommonConstants.SHOW_ACCOUNT;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
///**
// * domain class to map Account db object
// * 
// * @author hgoel2
// *
// */
//
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = ACCOUNT)
//public class Account {
//	@Id
//	@GeneratedValue
//	@Column(name = ID, updatable = false, nullable = false)
//	private Long id;
//
//	@Column(name = ACCOUNT_NAME)
//	private String accountName;
//
//	@Column(name = SHOW_ACCOUNT, nullable = false, length = 5)
//	private String showAccount;
//}
//
////	/**
////	 * QuestionsResponseDTO holds Questions APIs response Information. Response holds responseHeader and responseBody
////	 * information.
////	 *
////	 */
////
////
////	public class QuestionsResponseDTO extends BaseResponseDTO<QuestionsResponseBodyDTO> {
////
////	  @Getter
////	  @Setter
////	  public class BaseRequestDTO<T> {
////
////	    private RequestHeaderDTO requestHeader;
////
////	    private T requestBody;
////
////	    public void initializeRequestDTO() {
////
////	    }
////
////	    @Override
////	    public String toString() {
////	      return "BaseRequestDTO [requestHeader=" + requestHeader + ", requestBody=" + requestBody + "]";
////	    }
////	    
////	    @JsonInclude(Include.NON_NULL)
////	    @Data
////	    public class ResponseHeaderDTO {
////
////
////	      private String transactionId;
////	      private Date responseTimeStamp;
////	      private String responseTime;
////	      private String responseStatus;
////	      private int responseCode;
////	      private ErrorDTO error;
////	      private List<ErrorDetailDTO> errordetails;
////	      private String version;
////	      @JsonDeserialize(using = LocalDateTimeDeserializer.class)
////	      @JsonSerialize(using = LocalDateTimeSerializer.class)
////	      @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
////	      private LocalDateTime timeStamp;
////	      
////	      public static ResponseHeaderDTO prepareResponseHeader(long startTime) {
////	        long endTime = System.currentTimeMillis();
////	        return prepareResponseHeaderObject(GeneralErrorEnum.SUCCESS, String.valueOf(endTime - startTime));
////	    }
////
////	    private static ResponseHeaderDTO prepareResponseHeaderObject(GeneralErrorEnum responseStatus, String responseTime) {
////	        ResponseHeaderDTO responseHeaderDTO = new ResponseHeaderDTO();
////	        responseHeaderDTO.setResponseStatus(responseStatus.getErrorMessage());
////	        responseHeaderDTO
////	            .setTransactionId(StringUtils.isEmpty(ApplicationContextManager.getApplicationContext().getTransactionId())
////	                ? UUID.randomUUID().toString() : ApplicationContextManager.getApplicationContext().getTransactionId());
////	        responseHeaderDTO.setResponseCode(responseStatus.getCode());
////	        if (null != responseTime) {
////	            responseHeaderDTO.setResponseTime(responseTime);
////	        }
////	        responseHeaderDTO.setResponseTimeStamp(new Date());
////	        ApplicationContextManager.getApplicationContext().setHttpResponseCode(String.valueOf(HttpStatus.OK.value()));
////	        ApplicationContextManager.getApplicationContext().setControllerResponseTime(responseTime);
////	        log.info("Complete Time Log {} ", ApplicationContextManager.getApplicationContext().getTimerInfoMessage());
////	        log.info(CommonConstants.RESPONSE_DETAILS,
////	            ApplicationContextManager.getApplicationContext().getLogInfoMessage());
////
////	        return responseHeaderDTO;
////	    }
////
////
////	}
