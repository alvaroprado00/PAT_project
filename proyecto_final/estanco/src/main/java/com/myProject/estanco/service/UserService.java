package com.myProject.estanco.service;

import java.util.List;

import com.myProject.estanco.model.Coment;
import com.myProject.estanco.model.User;
import com.myProject.estanco.model.UserComent;

/**
 * Interfaz en la que se especifican los metodos que es necesario que implemente cualquier service tipo userService
 * 
 */
public interface UserService {
	

	public List<User> getAllUsers();
	
	public User checkUser(User userToCheck, String type);
	
	public List<Coment> getComents(String userName);
	
	public User setNewComent(UserComent userComent);
	
	public User registerUser(User user);
}
