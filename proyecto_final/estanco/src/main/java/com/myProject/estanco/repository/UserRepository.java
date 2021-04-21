package com.myProject.estanco.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.myProject.estanco.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	//Con el limit 1 le digo que solo me devuelva una row
	
	@Query("SELECT * FROM USERS WHERE user_name= :userN LIMIT 1")
	public User findUserByUserName(@Param("userN") String userName);
	
	@Query("SELECT * FROM USERS WHERE user_name= :userN AND password= :userP LIMIT 1")
	public User findUserByUserNameAndPassword(@Param("userN") String userName, @Param("userP") String userPassword);
	
}
