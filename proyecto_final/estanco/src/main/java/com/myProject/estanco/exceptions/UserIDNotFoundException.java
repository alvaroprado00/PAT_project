package com.myProject.estanco.exceptions;

import com.myProject.estanco.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserIDNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIDNotFoundException(User u) {
		
		super("No se ha encontrado el ID de: "+u.getUserName());
	}
}
