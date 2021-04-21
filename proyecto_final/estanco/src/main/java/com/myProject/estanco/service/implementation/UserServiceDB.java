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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnExpression("'${service-user-up}'=='DB'")
public class UserServiceDB implements UserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers(){return null;}
	
		
	@Override
	public User checkUser(User userToCheck, String type) {
		
		String userName=userToCheck.getUserName();
		String userPassword=userToCheck.getPassword();
		
		User userChecked;
		
		if(type=="strict") {
		  userChecked=userRepository.findUserByUserNameAndPassword(userName, userPassword);
			
		}else if(type=="relaxed") {
		  userChecked=userRepository.findUserByUserName(userName);
		}else {
		  userChecked=null;
		}
		
		
		return userChecked;
	}
		
	@Override
	public User setNewComent(UserComent userComent) {
		
		//En este metodo utilizo el metodo por defecto de update del Crud Repository
		//Es decir cojo de la lista del programa el user que me pasan le añado el coment y hago un add a la DB
		
		
		User userToAddComent=userRepository.findUserByUserName(userComent.getUserName());
		
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
		
		String userName=user.getUserName();
		//Comprobamos en la memoria de programa si ya existe
		userChecked=userRepository.findUserByUserName(userName);
		
		//En caso de que no, lo guardamos en la DB y actualizamos memoria de programa
		if(userChecked==null) {
			 userResponse=userRepository.save(user);
			 this.Init();
		}
		
		//Usuario de la DB o null si no se ha hecho la act
		return userResponse;
		
		
	}
	
	@Override
	public User savePurchase(UserPurchase userPurchase) {
		
		//obtengo la entidad usuario completa ya existente
		User userToUpdate=userRepository.findUserByUserName(userPurchase.getUserName());
		
		//No compruebo si existe porque Tiene que existir si me esta mandando esta info el front
		
		//Añado a esta una nueva compra formada por las lineas de compra especificadas
		userToUpdate.setNewPurchase(userPurchase.getLineasCompra());
			
		//Actualizo DB
		User userResponse= userRepository.save(userToUpdate);
			
		return userResponse;
		
	}
	
	
	
	@Override
	public List<Coment> getComents(String userName){
		
		//Me creo un usuario con el campo de userName relleno unicamente
		User userReceived =userRepository.findUserByUserName(userName);
		
		//De primeras la respuesta es null y si encuentra algo ya le paso la lista de verdad
		List<Coment> response =null;
		
		if(userReceived!=null) {
		
			response= userReceived.getComents();
			
		}
		
		return response;
		
	}
	
	
	@PostConstruct
	public void Init(){
		log.debug("Se levanta el userServiceDB!");
	}
	
}
