package com.myProject.estanco.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.model.*;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ArticleService {

	
	@Value("${tabacoIndustrial.url}")
	private String tabacoIndustrialURL;
	
	@Value("${tabacoLiar.url}")
	private String tabacoLiarURL;
	
	private TabacoIndustrialSearchModel industrialSearchModel;
	private TabacoLiarSearchModel liarSearchModel;
	
	public TabacoIndustrialSearchModel getFromAPITabacoIndustrial() {
		
		//Para comprobar que llega a este metodo meto un log
		
		log.debug("Estoy llegando al metodo getAllTabacoIndustrial");
		
		RestTemplate template = new RestTemplate();
		
		HttpMethod metodo= HttpMethod.GET;
		
		ResponseEntity<TabacoIndustrialSearchModel> response =template.exchange(tabacoIndustrialURL,metodo, null, TabacoIndustrialSearchModel.class);
		
		//Importante, si devuelves la peticion entera al controller pasa una movida de cabcera ok y tarda mazo tiempo
		//Pasamos el body y luego pones el status a ok en controller
		return response.getBody();
		
	}
	
	public TabacoLiarSearchModel getFromAPITabacoLiar() {
		

		log.debug("Estoy llegando al metodo getAllTabacoLiar");
		
		RestTemplate template = new RestTemplate();
		
		HttpMethod metodo= HttpMethod.GET;
		
		ResponseEntity<TabacoLiarSearchModel> response =template.exchange(tabacoLiarURL,metodo, null, TabacoLiarSearchModel.class);
		

		return response.getBody();
	}
	
	
	
	public TabacoIndustrialSearchModel getTabacoIndustrialSearch(){
		//Devuelvo un objeto tipo tabaco industrial Search model que es mas facil de manejar que una lista de objects
		return industrialSearchModel;
	}
	
	
	public TabacoLiarSearchModel getTabacoLiarSearch(){
		
		return liarSearchModel;
	}
	
	
	@PostConstruct
	public void inicializeTabacos() {
		industrialSearchModel=getFromAPITabacoIndustrial();
		liarSearchModel=getFromAPITabacoLiar();
	}
}
