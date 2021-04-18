package com.myProject.estanco.service;

import java.util.List;

import com.myProject.estanco.model.Coment;
import com.myProject.estanco.model.User;
import com.myProject.estanco.model.UserComent;
import com.myProject.estanco.model.UserPurchase;

/**
 * Interfaz en la que se especifican los metodos que es necesario que implemente cualquier service tipo userService
 * 
 */
public interface UserService {
	
	/**
	 * Metodo para obtener todos los usuarios registrados 
	 * @return lista de usuarios registrados
	 */
	public List<User> getAllUsers();
	
	/**
	 * Metodo para comprobar si existe un usuario ya registrado
	 * 
	 * @param userlogin objeto tipo userLogin con solo dos atributos: userName y password
	 * @param type la opcion "strict" comprueba si hay un usuario con mismo userName y password. La opcion "relaxed"
	 * comprueba si hay un usario con mismo userName unicamente
	 * @return devuelve el usuario si existe en la lista de Usuarios y si se han indicado correctamente los parametros y si no devuelve NULL.
	 */
	public User checkUser(User userToCheck, String type);
	
	
	/**
	 * Método para coger los comentarios de un usuario de la aplicación
	 * @param userName nombre de usuario del cual se quieren los coments
	 * @return si el usuario esta en la DB devuelve una lista de comentarios. Si no devuelve null.
	 */
	public List<Coment> getComents(String userName);
	
	
	/**
	 * Método para añadir un comentario realizado en la web por un usuario logeado
	 *
	 * @param userComent es un pojo intermedio formado por el nombre del usuario y el comentario realizado en la web
	 * @return el usuario completo con todos los datos + el nuevo coment
	 */
	public User setNewComent(UserComent userComent);
	
	
	
	/**
	 * Metodo para registrar un nuevo usuario en la API
	 * 
	 * @param user Recibe un usuario completo para registrarlo
	 * @return Te devuelve el mismo usuario si lo ha dado de alta y te devuelve un null si no le ha dado de alta porque ya 
	 *  		existia alguien con ese nombre de usuario
	 */
	public User registerUser(User user);
	
	/**
	 * Metodo para registrar una cpmpra de un usuario en la API
	 * 
	 * @param userPurchase Recibe un usuario con el nombre de user y sus lineas de compra
	 * @return Te devuelve el mismo usuario si añade las lineas y te devuelve un null si no
	 */
	public User savePurchase(UserPurchase userPurchase);
	
}
