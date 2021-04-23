package com.myProject.estanco.service.implementation;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.myProject.estanco.model.GIF;
import com.myProject.estanco.repository.GIFRepository;
import com.myProject.estanco.service.GIFService;

import static org.assertj.core.api.BDDAssertions.then;

public class GIFServiceDBTest {
	
	@Autowired
	GIFRepository gifRepository;
	
	final GIFService service = new GIFServiceDB();
	
	@Test
	public void given_service_when_calling_getGif_then_Ok() {
		
		//GIVEN
		
		//The GIFServiceDB
		
		//WHEN aqui va la respuesta de verdad
		
		GIF responseFromService= service.getGIF();
				
		//THEN aqui creas la respuesta esperada y comparas
		
		GIF expectedResponse= new GIF(1, "gifEjemplo", "https://m.gifmania.com/Gif-Animados-Objetos/Imagenes-Tabaco/Cigarros/Cigarro-Amarillo-72337.gif");
		
		then(responseFromService).isEqualTo(expectedResponse);
	}
	

}
