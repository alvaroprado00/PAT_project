package com.myProject.estanco.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.myProject.estanco.model.UserPurchase;
import com.myProject.estanco.model.UserPurchaseInfo;
import com.myProject.estanco.model.UserToUpdate;
import com.myProject.estanco.service.UserService;
import com.myProject.estanco.service.GIFService;
import com.myProject.estanco.service.implementation.ArticleService;
import com.myProject.estanco.service.implementation.TabacoIndustrialServiceDB;
import com.myProject.estanco.service.implementation.TabacoLiarServiceDB;

import lombok.extern.slf4j.Slf4j;



//Indico con anotaciones que es un controller tipo REST y que va a atender las peticiones a /api
//Indico que esta clase va usar logs (en properties tienes que indicar el nivel de prioridad)
@Slf4j
@RestController
@RequestMapping("/api")
public class RestControllerDemo {
	
	
	//Inyecto dependencias de los servicios
	@Autowired
	private UserService userService;
	
	//@Autowired
	//private ArticleService articleService;
	
	@Autowired
	private GIFService gifService;
	
	@Autowired
	private TabacoLiarServiceDB tabacoLiarService;
	
	@Autowired
	private TabacoIndustrialServiceDB tabacoIndustrialService;	
	
	
	@PostMapping("/users/login")
	public ResponseEntity<User> loginUser(@Valid @RequestBody UserLogin userLogin){
		
		log.debug("Llego al login en el controller");
		
		User userToCheck= new User(userLogin);
		
		User user=userService.checkUser(userToCheck, "strict");
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
		
		User userCompleto=userService.setNewComent(userComent);
		
		ResponseEntity<User> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(userCompleto!=null) {
			response= new ResponseEntity<User>(userCompleto, HttpStatus.OK);
		}
		
		return response;
	}
	
	@PostMapping("user/purchase")
	public ResponseEntity<User> finishPurchase(@Valid @RequestBody UserPurchase userPurchase){
		
		log.debug("Llego a finalizar compra en el controller");
		
		User userCompleto = userService.savePurchase(userPurchase);
		
		ResponseEntity<User> response =  new ResponseEntity<User>(userCompleto, HttpStatus.OK);
		
		return response;
				
	}
	
	//Con el Request Param le indicas que la info de userName viene en la url: users/coments?userName=alvaroprado00
	@GetMapping("users/coments")
	public ResponseEntity<List<Coment>> getComents(@RequestParam("userName") String userName){
		
		List<Coment> comentarios = userService.getComents(userName);
		
		
		ResponseEntity<List<Coment>> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if (comentarios!= null) {
			
			response=new ResponseEntity<>(comentarios, HttpStatus.OK);
		}
		
		return response;
	}
	
	
	
	@GetMapping("/tabacoIndustrial")
	public ResponseEntity<TabacoIndustrialSearchModel> getTabacoIndustrial(){
		
		
		//log.debug("Llego al controller metodo getTabacoIndustrial");
		
		//TabacoIndustrialSearchModel industrialSearch= articleService.getTabacoIndustrialSearch();
		
		//ResponseEntity<TabacoIndustrialSearchModel> response= new ResponseEntity<>(industrialSearch, HttpStatus.OK);
		
		ResponseEntity<TabacoIndustrialSearchModel> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);

		TabacoIndustrialSearchModel tabacoLiar = tabacoIndustrialService.getTabacoIndustrial();
		
		if(tabacoLiar != null) {
			response = new ResponseEntity<>(tabacoLiar,HttpStatus.OK);
		}
		
		return response;
		
	}
	
	@GetMapping("GIF")
	public ResponseEntity<GIF> getGIF(){
		
		ResponseEntity<GIF> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		GIF gifResponse=gifService.getGIF();
		
		
		if(gifResponse!=null) {
			response= new ResponseEntity<>(gifResponse, HttpStatus.OK);
		}
		
		return response;
	}
	
	@GetMapping("/tabacoLiar")
	public ResponseEntity<TabacoLiarSearchModel> getTabacoLiar(){
		
		//log.debug("LLego al controller metodo getTabacoLiar");
		
		//TabacoLiarSearchModel liarSearchModel= articleService.getTabacoLiarSearch();
		
		//ResponseEntity<TabacoLiarSearchModel> response= new ResponseEntity<>(liarSearchModel, HttpStatus.OK);
		
		ResponseEntity<TabacoLiarSearchModel> response= new ResponseEntity<>(HttpStatus.NOT_FOUND);

		TabacoLiarSearchModel tabacoLiar = tabacoLiarService.getTabacoLiar();
		
		if(tabacoLiar != null) {
			response = new ResponseEntity<>(tabacoLiar,HttpStatus.OK);
		}
		
		return response;
	}
	
	
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody User user){
		
		log.debug("Llego al register del controller");
		ResponseEntity<User> response= new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		User serviceResponse= userService.registerUser(user);
		
		if(serviceResponse!=null) {
			//Respuesta no nula del service=> Ha sido registrado
			response= new ResponseEntity<>(serviceResponse, HttpStatus.OK);
		}
		return response;		
	}
	
	
	
	@GetMapping("/users/userInfo/{userName}")
	public ResponseEntity<User> getInfoFromUser(@PathVariable String userName) {
		
		ResponseEntity<User> response =new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		User userInfo= userService.checkUser(new User(userName), "relaxed");
		
		if(userInfo!=null) {
			response= new ResponseEntity<>(userInfo, HttpStatus.OK);
			
		}
		
		return response;
	}
	
	@PostMapping("/users/userInfo")
	public ResponseEntity<User> updateInfoUser(@RequestBody UserToUpdate userToUp){
		
		log.debug("llego al metodo del controller de update");
		User u= userService.updateUser(userToUp);
		
		ResponseEntity<User> response= new ResponseEntity<>(HttpStatus.CONFLICT);
		
		if(u!=null) {
			response=new ResponseEntity<>(u, HttpStatus.OK);
		}
		
		return response;
			
	}
	


}
