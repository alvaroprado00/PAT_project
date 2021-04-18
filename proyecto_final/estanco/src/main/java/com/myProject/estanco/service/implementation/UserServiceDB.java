package com.myProject.estanco.service.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import com.myProject.estanco.model.Coment;
import com.myProject.estanco.model.User;
import com.myProject.estanco.model.UserComent;
import com.myProject.estanco.model.UserLogin;
import com.myProject.estanco.model.UserPurchase;
import com.myProject.estanco.repository.UserRepository;
import com.myProject.estanco.service.UserService;

@Service
@ConditionalOnExpression("'${service-user-up}'=='DB'")
public class UserServiceDB implements UserService {
	
	//Nos creamos a nivel de clase service una lista de todos los usuarios para tenerla en la memoria del programa
	private List<User> listaUsuarios;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers(){
		
		Iterable<User> allUsers= userRepository.findAll();
		
		//Pasamos de un iterable a un ArrayList
		
		Iterator<User> i= allUsers.iterator();
		
		ArrayList<User> usersList= new ArrayList<>();
		while(i.hasNext()) {
			usersList.add(i.next());
		}
		
		return usersList;
		
	}
		
		
	@Override
	public User setNewComent(UserComent userComent) {
		
		//En este metodo utilizo el metodo por defecto de update del Crud Repository
		//Es decir cojo de la lista del programa el user que me pasan le añado el coment y hago un add a la DB
		
		User userToAddComent= new User(userComent);
		
		userToAddComent=checkUser(userToAddComent, "relaxed");
		
		if (userToAddComent!=null) {
			
			userToAddComent.getComents().add(userComent.getComent());
			
			userToAddComent=userRepository.save(userToAddComent);
			
			//Antes de nada reinicio memoria de programa;
			
			this.Init();
		}
		

		
		return userToAddComent;
		
		
	}
	
	@Override
	public User registerUser(User user) {
		
		User userChecked =null;
		User userResponse=null;
		
		//Comprobamos en la memoria de programa si ya existe
		userChecked=checkUser(user, "relaxed");
		
		//En caso de que no, lo guardamos en la DB y actualizamos memoria de programa
		if(userChecked==null) {
			 userResponse=userRepository.save(user);
			 this.Init();
		}
		
		//Usuario de la DB o null si no se ha hecho la act
		return userResponse;
		
		
	}
	
	public User savePurchase(UserPurchase userPurchase) {
		
		//obtengo la entidad usuario completa ya existente
		User userToUpdate=checkUser(new User(userPurchase), "relaxed");
		
		//No compruebo si existe porque Tiene que existir si me esta mandando esta info el front
		
		//Añado a esta una nueva compra formada por las lineas de compra especificadas
		userToUpdate.setNewPurchase(userPurchase.getLineasCompra());
			
		//Actualizo DB
		User userResponse= userRepository.save(userToUpdate);
			
		return userResponse;
		
	}
	
	
	
	//LOS DOS METODOS SIGUIENTES SON IGUALES QUE LOS METODOS DEL OTRO SERVICE YA QUE UTILIZAN LA LISTA DE MEMORIA DE PROGRAMA
	
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
		
			response= userAnswer.getComents();
			
		}
		
		return response;
		
	}
	
	
	@PostConstruct
	public void Init(){
		listaUsuarios=this.getAllUsers();
	}
	
}
