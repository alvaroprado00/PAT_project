package com.myProject.estanco.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ShoppingCart {
	
	private int numberOfPurchases;
	private List<Purchase> purchases;
	
	public ShoppingCart() {
		numberOfPurchases=0;
		purchases=new ArrayList<>();
	}
	
	public void refreshNumber() {
		
		this.numberOfPurchases++;
	}
}
