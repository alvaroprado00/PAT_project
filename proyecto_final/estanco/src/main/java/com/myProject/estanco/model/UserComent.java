package com.myProject.estanco.model;

import lombok.Data;

/**
 * 
 * Clase para que el front nos pueda mandar un usuario especificado unicamente por su nombre de usuario y el comentario que va a hacer 
 *
 */

@Data
public class UserComent {
	
	private String userName;
	private Coment coment;
}
