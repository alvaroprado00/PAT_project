package com.myProject.estanco.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.myProject.estanco.model.UserPurchaseInfo;

public interface RepositoryPurchases extends CrudRepository<UserPurchaseInfo,Long> {
	
	/*@Transactional
	@Modifying
	@Query("insert into usersPurchase values('firstName','lastName','email','address','country','zip')")
	public boolean savePurchase(@Param('firstName') String firstName,@Param('lastName') String lastName,@Param('email') String email,@Param('country') String country,@Param('zip') int email) {
		
	}
	*/
	
	
	
}
