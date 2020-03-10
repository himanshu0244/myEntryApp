/*
 * Admin.java
 */
package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ADMIN;
import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.PASSWORD;
import static org.myEntryApp.server.constants.CommonConstants.USERNAME;

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
 * domain class to map Admin db object
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
@Table(name = ADMIN)
public class Admin {

	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;

	@Column(name = USERNAME, length = 16, nullable = false)
	private String userName;

	@Column(name = PASSWORD, length = 16, nullable = false)
	private String password;
}
