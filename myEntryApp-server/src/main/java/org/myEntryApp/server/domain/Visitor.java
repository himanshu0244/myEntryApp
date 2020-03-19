package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
import static org.myEntryApp.server.constants.CommonConstants.CONTACT_NUMNER;
import static org.myEntryApp.server.constants.CommonConstants.EMP_ID;
import static org.myEntryApp.server.constants.CommonConstants.ESCORT_EMP_ID;
import static org.myEntryApp.server.constants.CommonConstants.FIRST_NAME;
import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.IMAGE;
import static org.myEntryApp.server.constants.CommonConstants.IN_TIME;
import static org.myEntryApp.server.constants.CommonConstants.LAPTOP_SERIAL_NUMNER;
import static org.myEntryApp.server.constants.CommonConstants.LAST_NAME;
import static org.myEntryApp.server.constants.CommonConstants.LOCATION_NAME;
import static org.myEntryApp.server.constants.CommonConstants.ODC_LOCATION;
import static org.myEntryApp.server.constants.CommonConstants.OUT_TIME;
import static org.myEntryApp.server.constants.CommonConstants.PROXY;
import static org.myEntryApp.server.constants.CommonConstants.PURPOSE;
import static org.myEntryApp.server.constants.CommonConstants.REMARKS;
import static org.myEntryApp.server.constants.CommonConstants.VISITOR;
import static org.myEntryApp.server.constants.CommonConstants.VISITOR_TYPE;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = VISITOR)
public class Visitor  extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = VISITOR_TYPE)
	private String visitorType;

	@Column(name = FIRST_NAME)
	private String firstName;

	@Column(name = LAST_NAME)
	private String lastName;

	@Column(name = CONTACT_NUMNER)
	private String contactNumber;

	@Column(name = PURPOSE)
	private String purpose;

	@Column(name = LOCATION_NAME)
	private String location;

	@Column(name = EMP_ID)
	private String visitorEmpId;

	@Column(name = ESCORT_EMP_ID)
	private String escortEmpId;

	@Column(name = LAPTOP_SERIAL_NUMNER)
	private String laptopSerialNo;

	@Column(name = REMARKS)
	private String remarks;

	@Column(name = IN_TIME)
	private Timestamp intime;

	@Column(name = OUT_TIME)
	private Timestamp outtime;

	@Column(name = OUT_TIME)
	private String empType;

	@Lob
	@Column(name = IMAGE)
	private byte[] image;

	@Column(name = ODC_LOCATION)
	private String odcLocation;

	@Column(name = ACCOUNT_NAME)
	private String account;

	@Column(name = PROXY)
	private String proxy;
}
