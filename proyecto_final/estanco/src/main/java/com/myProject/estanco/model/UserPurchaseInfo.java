package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("usersPurchases")
@Data
public class UserPurchaseInfo {

	 @Id
	 private long id;
	 
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String address;
	 private String country;
	 private int zip;
	 private Tarjeta tarjeta;

}
