package com.myProject.estanco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.myProject.estanco.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	
	
}
