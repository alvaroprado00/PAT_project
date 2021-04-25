package com.myProject.estanco.repository;

import org.springframework.data.repository.CrudRepository;

import com.myProject.estanco.model.TabacoLiar;

public interface TabacoLiarRepository extends CrudRepository<TabacoLiar,Long> {

	//Al extender de CrudRepository hay varios metodos que estan ya implementados
	
		//findAll() te devuelve todo lo que encuentre en la DB
		//deleteAll() te borra todo 
		//findById(ID id)
		//deleteById(ID id)
		//find(Entity E)
		//delete(Entity E)
	
}
