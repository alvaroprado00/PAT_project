package com.myProject.estanco.repository;


import org.springframework.data.repository.CrudRepository;


import com.myProject.estanco.model.GIF;

public interface GIFRepository extends CrudRepository<GIF,String> {
	
	//Al extender de CrudRepository hay varios metodos que estan ya implementados
	
	//findAll() te devuelve todo lo que encuentre en la DB
	//deleteAll() te borra todo 
	//findById(ID id)
	//deleteById(ID id)
	//find(Entity E)
	//delete(Entity E)
	
}
