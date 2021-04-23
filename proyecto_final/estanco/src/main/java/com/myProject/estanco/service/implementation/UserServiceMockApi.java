package com.myProject.estanco.service.implementation;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.exceptions.UserIDNotFoundException;
import com.myProject.estanco.model.*;
import com.myProject.estanco.service.UserService;

import lombok.extern.slf4j.Slf4j;



//Tendra la logica de negocio de usuarios por eso es un service

//Con el @ConditionalOnExpression hacemos que solo se levante el userService indicado en la properties

 @Slf4j
@Service
@ConditionalOnExpression("'${service-user-up}'=='MOCKAPI'")
public class UserServiceMockApi implements UserService {
	
	
	//con la  anotacion de @value inyecto el valor de la URL de la API desde properties
	@Value("${users.url}")
	private String usersUrl;
	
	private List<User> listaUsuarios;
	
	//Not implemented
	@Override
	public User updateUser(UserToUpdate u) {
		return null;
	}
	
	@Override
	public List<User> getAllUsers(){
			
		//Llamo a mi fake Api y me cojo todos los usuarios para guardarmelos
	
		
		//Con este objeto spring se hacen las peticiciones HTTP
		final RestTemplate template =new RestTemplate();
		
		//Me creo un objeto que indica el metodo HTTP
		final HttpMethod metodoHttp= HttpMethod.GET;
		
		
		//Hago la llamada a la api
		//Se le indica la URL, el metodo http y la clase java a la que tiene que convertir el JSON
		//Al ser metodo GET el tercer parametro puede estar a null
		final ResponseEntity<UserSearchModel> respuesta= template.exchange(usersUrl, metodoHttp, null, UserSearchModel.class);
		
		return respuesta.getBody().getItems();
	}
	
	
	@Override
	public User checkUser(User userToCheck, String type) {
		
		
		User userResponse=null;
		
		
		//Para evitar problema de case Sensitive
		type=type.toLowerCase();
			
		for(User u: listaUsuarios) {
			
			if (u.equalsTo(userToCheck, type)) {
				
				//Si el type es strict tiene que coincidir userName y password 
				//Si el type es Relaxed solo userName
				//Si le pasas cualquier otro type te devuelve false
				
				userResponse=u;
				
			}
		}
		
		//Respondo al controller con el usuario

		return userResponse;
	}
	
	
	
	@Override
	public List<Coment> getComents(String userName){
		
		//Me creo un usuario con el campo de userName relleno unicamente
		User userReceived= new User(userName);
		
		//Con checkUser me lo devuelve si esta en la memoria de programa
		User userAnswer= this.checkUser(userReceived, "relaxed");
		
		//De primeras la respuesta es null y si encuentra algo ya le paso la lista de verdad
		List<Coment> response =null;
		
		if(userAnswer!=null) {
			
			response=new ArrayList<>();
			Set<Coment> comentsSet= userAnswer.getComents();
			response.addAll(comentsSet);
			
		}
		
		return response;
	}
	
	
	@Override
	public User setNewComent(UserComent userComent) {
		
		User userToSetNewComent= new User(userComent);
		
		//Busco el usuario en la memoria de programa
		User userComplete= this.checkUser(userToSetNewComent, "relaxed");
		
		User responseUser=null;
		
		long id=userComplete.getId();
		
		//Añado al usuario que esta en la base de datos el nuevo coment
		
		userComplete.getComents().add(userComent.getComent());
		
		//Hago PUT a la API
			
		RestTemplate template= new RestTemplate();
		HttpMethod metodo= HttpMethod.PUT;
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json");
		HttpEntity<User> entity= new HttpEntity<>(userComplete, headers);
		
		String urlCompleta= usersUrl+"/"+String.valueOf(id);
		
		ResponseEntity<User> response= template.exchange(urlCompleta, metodo, entity, User.class);
		
		responseUser=response.getBody();
		
		//Volvemos a llamar a inicializeUsers para que en la memoria de programa se registre esto
		this.inicializeUsers();
		
		return responseUser;
		
	}
	

	public User savePurchase(UserPurchase userPurchase) {
		
		User userToSetPurchase= new User(userPurchase);
		
		//Busco el usuario en la memoria de programa
		User userComplete= this.checkUser(userToSetPurchase, "relaxed");
		
		User responseUser=null;
		
	
		long id=userComplete.getId();
		
		//Añado al usuario que esta en la base de datos la nueva purchase
		
		userComplete.setNewPurchase(userPurchase.getLineasCompra());
		
		//Hago PUT a la API
			
		RestTemplate template= new RestTemplate();
		HttpMethod metodo= HttpMethod.PUT;
		
		HttpHeaders headers= new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Accept", "application/json");
		HttpEntity<User> entity= new HttpEntity<>(userComplete, headers);
		
		String urlCompleta= usersUrl+"/"+String.valueOf(id);
		
		ResponseEntity<User> response= template.exchange(urlCompleta, metodo, entity, User.class);
		
		responseUser=response.getBody();
		
		//Volvemos a llamar a inicializeUsers para que en la memoria de programa se registre esto
		this.inicializeUsers();
		
		
		return responseUser;
			
	}
	
	@Override
	public User registerUser(User user){
		
		//Importante: si se quiere registrar alguien y ya hay alguien con ese nombre de usuario no le dejas
		
		
		//Comprobamos si ya alguien tiene ese nombre de Usuario
		User userChecked= this.checkUser(user, "relaxed");
		
		User response=null;
		
		if(userChecked==null) {
			
			//Nadie tiene ese nombre de usuario asi que procedemos a hacer POST
			
			//Va a tratarse de un POST a la API por tanto tiene que haber headers
			
			RestTemplate template= new RestTemplate();
			
			HttpMethod method= HttpMethod.POST;
			
			//Me creo la cabecera de la peticion
			HttpHeaders headers= new HttpHeaders();
			
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			
			//Otra manera:
			// headers.setContentType(MediaType.JSON);
			
			//Seteo el body que es el usuario que me pasan
			
			User body = user;
			
			HttpEntity<User> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<User> responseFromAPI = template.exchange(usersUrl, method, entity, User.class);
			
			response=responseFromAPI.getBody();
			
	
			//Ojo, que si registro un nuevo usuario, debo volver a ejecutar InicializeUsers
			inicializeUsers();
			
		}
		
		return response;
		
	}
	
	
	//Despues de inicializar el bean me cojo todos los usuarios de la API para tenerlos ya en memoria
	@PostConstruct
	public void inicializeUsers() {
		
		log.debug("Inicializando lista de usuarios en memoria de programa...");
		listaUsuarios=this.getAllUsers();
	
	}
	
	
}
