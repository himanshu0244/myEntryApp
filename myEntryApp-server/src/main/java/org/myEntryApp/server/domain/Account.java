/*
 * Account.java
 */
package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT;
import static org.myEntryApp.server.constants.CommonConstants.ACCOUNT_NAME;
import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.SHOW_ACCOUNT;

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
 * domain class to map Account db object
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
@Table(name = ACCOUNT)
public class Account {
	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;

	@Column(name = ACCOUNT_NAME)
	private String accountName;

	@Column(name = SHOW_ACCOUNT, nullable = false, length = 5)
	private String showAccount;

}
