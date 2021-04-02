package com.myProject.estanco.service;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myProject.estanco.exceptions.UserIDNotFoundException;
import com.myProject.estanco.model.*;

import lombok.extern.slf4j.Slf4j;



//Tendra la logica de negocio de usuarios por eso es un service
 @Slf4j
@Service
public class UserService {
	
	
	//con la  anotacion de @value inyecto el valor de la URL de la API desde properties
	@Value("${users.url}")
	private String usersUrl;
	
	private List<User> listaUsuarios;
	
	/**
	 * Metodo para obtener todos los usuarios registrados de mi API
	 * @return lista de usuarios registrados
	 */
	
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
	
	
	/**
	 * Metodo para comprobar si existe un usuario ya registrado
	 * 
	 * @param userlogin objeto tipo userLogin con solo dos atributos: userName y password
	 * @param type la opcion "strict" comprueba si hay un usuario con mismo userName y password. La opcion "relaxed"
	 * comprueba si hay un usario con mismo userName unicamente
	 * @return devuelve el usuario si existe en la lista de Usuarios y si se han indicado correctamente los parametros y si no devuelve NULL.
	 */
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
	
	
	/**
	 * Metodo para encontrar el UserID y poder hacer un PUT
	 * @param user usuario cuyo ID quieres averiguar
	 * @return el ID en la lista de users del usuario pasador por parámetro
	 */
	public int userIDinList(User user) throws UserIDNotFoundException {
		int contador=0;
		int id=0;
		for(User u: listaUsuarios) {
			contador++;
			if (u.equalsTo(user, "strict")) {
				
				id=contador;
				
			}
		}
		
		if(id==0) {
			throw new UserIDNotFoundException(user);
		}
		
		return id;
	}
	
	/**
	 * Método para añadir un comentario realizado en la web por un usuario logeado
	 *
	 * @param userComent es un pojo intermedio formado por el nombre del usuario y el comentario realizado en la web
	 * @return el usuario completo con todos los datos + el nuevo coment
	 */
	public User setNewComent(UserComent userComent) {
		
		User userToSetNewComent= new User(userComent);
		
		//Busco el usuario en la memoria de programa
		User userComplete= this.checkUser(userToSetNewComent, "relaxed");
		
		User responseUser=null;
		
		try {
			int id=this.userIDinList(userComplete);
			
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
			
		
			
		}catch(UserIDNotFoundException uide) {
			
			log.warn(uide.getMessage());
		}
		
		return responseUser;
		
	}
	
	
	/**
	 * Metodo para registrar un nuevo usuario en la API
	 * 
	 * @param user Recibe un usuario completo para registrarlo
	 * @return Te devuelve el mismo usuario si lo ha dado de alta y te devuelve un null si no le ha dado de alta porque ya 
	 *  		existia alguien con ese nombre de usuario
	 */
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
