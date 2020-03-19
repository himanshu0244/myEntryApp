package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.ID;
import static org.myEntryApp.server.constants.CommonConstants.LOCATION;
import static org.myEntryApp.server.constants.CommonConstants.LOCATION_NAME;

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
@Table(name = LOCATION)
public class Location {
	@Id
	@GeneratedValue
	@Column(name = ID, updatable = false, nullable = false)
	private Long id;

	@Column(name = LOCATION_NAME)
	private String locationName;

}
