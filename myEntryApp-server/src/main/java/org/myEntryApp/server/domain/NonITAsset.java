package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
import static org.myEntryApp.server.constants.CommonConstants.DESCRIPTION;
import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.IMAGE;
import static org.myEntryApp.server.constants.CommonConstants.LOCATION;
import static org.myEntryApp.server.constants.CommonConstants.MOVEMENT_DATE;
import static org.myEntryApp.server.constants.CommonConstants.MOVEMENT_ID;
import static org.myEntryApp.server.constants.CommonConstants.MOVEMENT_TYPE;
import static org.myEntryApp.server.constants.CommonConstants.NON_ITASSET;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * domain class to map Asset db object
 * 
 * @author hgoel2
 *
 */
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = NON_ITASSET)
public class NonITAsset {
	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;

	@Column(name = MOVEMENT_ID)
	private String movementId;
	@Column(name = MOVEMENT_TYPE)
	private String movementType;
	@Column(name = MOVEMENT_DATE)
	private Timestamp movementDate;
//	@Column(name = EMP_ID)
//	private String empId;
	@Column(name = ACCOUNT_NAME)
	private String account;
	@Column(name = LOCATION)
	private String location;
	@Column(name = IMAGE)
	private byte[] image;
	@Column(name = DESCRIPTION)
	private String description;

}
