package com.myProject.estanco.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
public class UserPurchaseInfo {
	 
	 public UserPurchaseInfo(UserPurchase user) {
		 this.address = user.getAddress();
		 this.firstName = user.getFirstName();
		 this.lastName = user.getLastName();
		 this.country = user.getCountry();
		 this.email = user.getEmail();
		 this.zip = user.getZip();
	 }
		
	 	@Column("first_name")
		@NotNull
		 private String firstName;

	 	@Column("last_name")
		@NotNull
		private String lastName;

	 	@Column("email") 
		@NotNull
		private String email;

	 	@Column("address")
		@NotNull
		private String address;

	 	@Column("country")
		@NotNull
		private String country;

	 	@Column("zip")		
		@NotNull
		private String zip;	

}
