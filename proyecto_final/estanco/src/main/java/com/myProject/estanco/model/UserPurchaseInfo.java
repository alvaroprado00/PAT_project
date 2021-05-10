package com.myProject.estanco.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("usersInfoPurchase")
public class UserPurchaseInfo {
	 
	 public UserPurchaseInfo(UserPurchase user) {
		 this.address = user.getAddress();
		 this.firstName = user.getFirstName();
		 this.lastName = user.getLastName();
		 this.country = user.getCountry();
		 this.email = user.getEmail();
		 this.zip = user.getZip();
	 }
	 
		@Id
		@Column("id_user_purchase")
		 private long id;
		
		@NotNull
		@Column("first_mame")
		 private String firstName;
		@NotNull
		@Column("last_name") 
		private String lastName;
		 
		@NotNull
		@Column("email")
		private String email;
		
		@NotNull
		@Column("address")
		private String address;
		
		@NotNull
		@Column("country")
		private String country;
		
		@NotNull
		@Column("zip")
		private String zip;	

}
