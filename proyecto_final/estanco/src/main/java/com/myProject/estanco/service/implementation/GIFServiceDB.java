package com.myProject.estanco.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import com.myProject.estanco.model.GIF;
import com.myProject.estanco.repository.GIFRepository;
import com.myProject.estanco.service.GIFService;

@Service("gifServiceDB")
@ConditionalOnExpression("'${service-gif-active}'=='DB'") //Mira el project properties y ve cual esta activo
public class GIFServiceDB implements GIFService{
	
	
	@Autowired
	GIFRepository gifRepository;
	
	@Override
	public GIF getGIF() {
		
		//Nos devuelve un iterable con todo lo que encuentra en la DB
		Iterable <GIF> gifs= gifRepository.findAll();
		
		//Como solo tenemos uno le decimos que nos devuelva ese
		return gifs.iterator().next();
		
	}
	
}
