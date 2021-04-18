package com.myProject.estanco.model;

import java.util.ArrayList;
import java.util.List;

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
	@MappedCollection(keyColumn="id_shopping_cart", idColumn="id_shopping_cart")
	private List<Purchase> purchases;
	
	public ShoppingCart() {
		numberOfPurchases=0;
		purchases=new ArrayList<>();
	}
	
	public void refreshNumber() {
		
		this.numberOfPurchases++;
	}
}
