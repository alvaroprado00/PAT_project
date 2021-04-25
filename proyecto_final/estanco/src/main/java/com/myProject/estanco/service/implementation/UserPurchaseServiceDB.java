package com.myProject.estanco.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.estanco.model.UserPurchaseInfo;
import com.myProject.estanco.repository.RepositoryPurchases;

@Service
public class UserPurchaseServiceDB {
	
	@Autowired
	RepositoryPurchases repository;
	
	public UserPurchaseInfo savePurchase(UserPurchaseInfo userPurchase) {
		
		//boolean situation = repository.savePurchase(userPurchase);
		
		return userPurchase;
		
	}
	
	
}
