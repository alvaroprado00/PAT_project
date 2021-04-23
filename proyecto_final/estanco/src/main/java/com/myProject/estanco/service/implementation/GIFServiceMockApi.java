package com.myProject.estanco.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.model.GIF;
import com.myProject.estanco.service.GIFService;

import java.util.List;


@Service
@ConditionalOnExpression("'${service-gif-active}' == 'MOCKAPI'")
public class GIFServiceMockApi implements GIFService {

	
	@Value("${GIF.url}")
	private String GIFUrl;
	
	private GIF myGIF;
	
	public List<GIF> getGIFFromAPI() {
		
		RestTemplate template= new RestTemplate();
		
		HttpMethod metodo= HttpMethod.GET;
		
		//IMPORTANTE PARA CONSEGUIR QUE TE LO MAPEE A UNA LISTA
		ResponseEntity<List<GIF>> responseEntity = template.exchange(GIFUrl, metodo, null, new ParameterizedTypeReference<List<GIF>>() {});

		List<GIF> gifs = responseEntity.getBody();
		
		return gifs;
	}
	
	
	@Override
	public GIF getGIF() {
		return this.myGIF;
	}
	
	
	@PostConstruct
	public void InicializeGIF() {
		
		myGIF=this.getGIFFromAPI().get(0);
	}
	
}
