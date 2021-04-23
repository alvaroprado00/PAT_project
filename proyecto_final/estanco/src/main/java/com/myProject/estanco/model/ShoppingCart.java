package com.myProject.estanco.model;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("shopping_carts")
public class ShoppingCart {
	
	@Id
	@Column("id_shopping_cart")
	private long id;
	@Column("number_purchases")
	private int numberOfPurchases;
	@MappedCollection(idColumn="id_shopping_cart")
	private Set<Purchase> purchases;
	
	public ShoppingCart() {
		numberOfPurchases=0;
		purchases=new HashSet<>();
	}
	
	public void refreshNumber() {
		
		this.numberOfPurchases++;
	}
}
