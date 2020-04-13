///*
// * Asset.java
// */
//package org.myEntryApp.server.domain;
//
//import static org.myEntryApp.server.constants.CommonConstants.ASSET;
//import static org.myEntryApp.server.constants.CommonConstants.ASSET_NUMBER;
//import static org.myEntryApp.server.constants.CommonConstants.ASSET_TYPE;
//import static org.myEntryApp.server.constants.CommonConstants.ID;
//import static org.myEntryApp.server.constants.CommonConstants.MOVEMENT_ID;
//
//import java.io.Serializable;
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
//
//@Getter
//@Setter
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = ASSET)
//public class Asset implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue
//	@Column(name = ID, updatable = false, nullable = false)
//	private Long id;
//
//	@Column(name = ASSET_NUMBER)
//	private String assetNumber;
//
//	@Column(name = ASSET_TYPE)
//	private String assetType;
//
//	@Column(name = MOVEMENT_ID)
//	private String movementId;
//}
