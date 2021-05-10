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

@Data
public class UserPurchase {
		
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
	
	@NotNull
	private String userName;
	
	@NotNull
	private Set<LineaCompra> lineasCompra;
	
}
