package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
import static org.myEntryApp.server.constants.CommonConstants.EMP_ID;
import static org.myEntryApp.server.constants.CommonConstants.ESCORT;
import static org.myEntryApp.server.constants.CommonConstants.FIRST_NAME;
import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.LAST_NAME;
import static org.myEntryApp.server.constants.CommonConstants.LOCATION;
import static org.myEntryApp.server.constants.CommonConstants.PASSWORD;

import java.io.Serializable;

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
@Table(name = ESCORT)
public class Escort implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;

//	@Column(name = EMP_ID)
//	private String empId;

	@Column(name = FIRST_NAME)
	private String firstName;

	@Column(name = LAST_NAME)
	private String lastName;

	@Column(name = PASSWORD)
	private String password;

	@Column(name = LOCATION)
	private String location;

	@Column(name = ACCOUNT_NAME)
	private String accountName;
}
