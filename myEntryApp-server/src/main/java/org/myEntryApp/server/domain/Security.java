//package org.myEntryApp.server.domain;
//
//import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
//import static org.myEntryApp.server.constants.CommonConstants.ID;
//import static org.myEntryApp.server.constants.CommonConstants.LOCATION;
//import static org.myEntryApp.server.constants.CommonConstants.PASSWORD;
//import static org.myEntryApp.server.constants.CommonConstants.SECURITY;
//import static org.myEntryApp.server.constants.CommonConstants.USERNAME;
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
// * domain class to map Asset db object
// * 
// * @author hgoel2
// *
// */
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = SECURITY)
//public class Security implements java.io.Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue
//	@Column(name = ID, updatable = false, nullable = false)
//	private Long id;
//
//	@Column(name = USERNAME)
//	private String userName;
//
//	@Column(name = PASSWORD)
//	private String password;
//
//	@Column(name = ACCOUNT_NAME)
//	private String accountName;
//
//	@Column(name = LOCATION)
//	private String location;
//
//}
