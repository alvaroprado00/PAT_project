package com.myProject.estanco.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.estanco.model.Coment;
import com.myProject.estanco.model.GIF;
import com.myProject.estanco.model.TabacoIndustrialSearchModel;
import com.myProject.estanco.model.TabacoLiarSearchModel;
import com.myProject.estanco.model.User;
import com.myProject.estanco.model.UserComent;
import com.myProject.estanco.model.UserLogin;
import com.myProject.estanco.service.UserService;
import com.myProject.estanco.service.implementation.ArticleService;
import com.myProject.estanco.service.implementation.GIFService;

import lombok.extern.slf4j.Slf4j;



//Indico con anotaciones que es un controller tipo REST y que va a atender las peticiones a /api
//Indico que esta clase va usar logs (en properties tienes que indicar el nivel de prioridad)
@Slf4j
@RestController
@RequestMapping("/api")
public class RestControllerDemo {
	
	
	//Inyecto dependencias de los servicios
	@Autowired
	private UserService userServiceMockApi;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private GIFService gifService;
	
	
	
	@PostMapping("/users/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody UserLogin userLogin){
		
		log.debug("Llego al login en el controller");
		
		//Check the user opcion strict significa que deben coincider password y userName
		
		User userToCheck= new User(userLogin);
		
		User user=userServiceMockApi.checkUser(userToCheck, "strict");
		ResponseEntity<User>response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(user!=null) {
			log.debug("He encontrado a: "+user.getUserName());
			response= new ResponseEntity<>(user, HttpStatus.OK);
		}
			
		//Si el usuario no esta registrado nos lo indica la cabecera con error 404
		return response;
	}
	
	@PostMapping("users/coments")
	public ResponseEntity<User> createNewComment(@RequestBody UserComent userComent){
		
		User userCompleto=userServiceMockApi.setNewComent(userComent);
		
		ResponseEntity<User> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(userCompleto!=null) {
			response= new ResponseEntity<User>(userCompleto, HttpStatus.OK);
		}
		
		return response;
	}
	
	//Con el Request Param le indicas que la info de userName viene en la url: users/coments?userName=alvaroprado00
	@GetMapping("users/coments")
	public ResponseEntity<List<Coment>> getComents(@RequestParam("userName") String userName){
		
		List<Coment> comentarios = userServiceMockApi.getComents(userName);
		
		
		ResponseEntity<List<Coment>> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if (comentarios!= null) {
			
			response=new ResponseEntity<>(comentarios, HttpStatus.OK);
		}
		
		return response;
	}
	
	
	
	@GetMapping("/tabacoIndustrial")
	public ResponseEntity<TabacoIndustrialSearchModel> getTabacoIndustrial(){
		
		
		log.debug("Llego al controller metodo getTabacoIndustrial");
		
		TabacoIndustrialSearchModel industrialSearch= articleService.getTabacoIndustrialSearch();
		
		ResponseEntity<TabacoIndustrialSearchModel> response= new ResponseEntity<>(industrialSearch, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/tabacoLiar")
	public ResponseEntity<TabacoLiarSearchModel> getTabacoLiar(){
		
		log.debug("LLego al controller metodo getTabacoLiar");
		
		TabacoLiarSearchModel liarSearchModel= articleService.getTabacoLiarSearch();
		
		ResponseEntity<TabacoLiarSearchModel> response= new ResponseEntity<>(liarSearchModel, HttpStatus.OK);
		
		return response;
	}
	
	
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody User user){
		
		log.debug("Llego al register del controller");
		ResponseEntity<User> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		User serviceResponse= userServiceMockApi.registerUser(user);
		
		if(serviceResponse!=null) {
			//Respuesta no nula del service=> Ha sido registrado
			response= new ResponseEntity<>(serviceResponse, HttpStatus.OK);
		}
		return response;		
	}
	
	
	@GetMapping("GIF")
	public ResponseEntity<GIF> getGIF(){
		 
		ResponseEntity<GIF> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		GIF gif= gifService.getGIFFromMemory();
		
		if(gif!=null) {
			
			response=new ResponseEntity<>(gif, HttpStatus.OK);
			
		}
		
		return response;
		
	}
	
	


}
