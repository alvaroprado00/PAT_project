package com.myProject.estanco.model;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * 
 * Clase para manejar los usuarios que van a comprar
 *
 */

@Table("usersPurchases")

@Data
public class UserPurchase {
	
	@Id
	@Column("id_user")
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
	
	@NotNull
	@Column("userName")
	private String userName;
	
	@NotNull
	@MappedCollection(idColumn="id_purchase")
	private Set<LineaCompra> lineasCompra;
	
}
