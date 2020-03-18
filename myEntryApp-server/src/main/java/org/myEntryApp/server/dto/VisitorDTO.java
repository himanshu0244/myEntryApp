package org.myEntryApp.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode
public class VisitorDTO {

	private String id;

	private String visitorType;

	private String firstName;

	private String lastName;

	private String contactNumber;

	private String purpose;

	private String location;

	private String visitorEmpId;

	@Override
	public String toString() {
		return "VisitorDTO [id=" + id + ", visitorType=" + visitorType + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contactNumber=" + contactNumber + ", purpose=" + purpose + ", location="
				+ location + ", visitorEmpId=" + visitorEmpId + "]";
	}
}
