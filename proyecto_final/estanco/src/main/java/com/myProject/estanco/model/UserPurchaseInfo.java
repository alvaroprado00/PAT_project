package com.myProject.estanco.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

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
		

		@NotNull
		private String firstName;

		@NotNull
		private String lastName;

		@NotNull
		private String email;

		@NotNull
		private String address;

		@NotNull
		private String country;

		@NotNull
		private String zip;	

}
