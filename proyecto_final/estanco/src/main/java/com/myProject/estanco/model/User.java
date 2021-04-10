package com.myProject.estanco.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;


//utilizo la anotacion Data que crea los getters, setters y constructor por defecto

@Data
@NoArgsConstructor
public class User {
	
	//Dato importante, al indicar un atributo como lista de Objetos(Coments)
	//Si en el mockapi lo construyes como array de JSON te lo deserializa perfecto
	
	@Nullable
	private long id;
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@Size(min=5)
	private String userName;
	
	@Size(min=5, max=15)
	private String password;
	
	@Size(min=10)
	private String email;
	@Nullable
	private List<Coment> coments;
	@Nullable 
	private ShoppingCart cart;

	//Al definirnos un constructor se va el que mete por defecto lombok
	//Hay que indicarle explicitamente que te cree uno con @NoArgsConstructor
	public User(UserLogin userLogin) {
		
		//constructor para crearme un User a partir de la clase intermedia UserLogin
		this.setFirstName("demo");
		this.setLastName("demo");
		this.setPassword(userLogin.getPassword());
		this.setUserName(userLogin.getUserName());
		this.setEmail("loquesea@gmail.com");
		ArrayList<Coment> coments= new ArrayList<>();
		coments.add(new Coment());
		this.setComents(coments);
	
	}
	
	public User(UserComent userComent) {
		
		this.setFirstName("demo");
		this.setLastName("demo");
		this.setPassword("demo");
		this.setUserName(userComent.getUserName());
		this.setEmail("loquesea@gmail.com");
		ArrayList<Coment> coments= new ArrayList<>();
		coments.add(userComent.getComent());
		this.setComents(coments);
	
	}

	public User(UserPurchase userPurchase) {
		
		this.setFirstName("demo");
		this.setLastName("demo");
		this.setPassword("demo");
		this.setUserName(userPurchase.getUserName());
		this.setEmail("loquesea@gmail.com");
		
	}
	
	public User(String userName) {
		this.setUserName(userName);
	}
	
	public boolean equalsTo(Object o, String type) {
		
		type= type.toLowerCase();
		boolean response;
		
		response=false;
		
		if(o instanceof User) {
			
			User u= (User)o;
			
			//Para evitar caseSensitive behaviours
			
			String userNameFromU=u.getUserName().toLowerCase();
			
			
			if(type.equals("strict")) {
				
				 //Si usamos un usuario con password= null da fallo
				
				String passwordFromU=u.getPassword().toLowerCase();
				if(userNameFromU.equals(this.getUserName().toLowerCase()) && passwordFromU.equals(this.getPassword().toLowerCase())) {
					
					response=true;
				}
			}else if(type.equals("relaxed")) {
				
				if(userNameFromU.equals(this.getUserName().toLowerCase())) {
					response=true;
				}
			}
			
		}
		
		return response;
	}
	
	
	public void setNewPurchase(List<LineaCompra> lineas){
		
		ShoppingCart cart=null;
		
		if(this.getCart()==null) {
			cart= new ShoppingCart();
		}else {
			cart= this.getCart();
		}
		
		
		Purchase newPurchase= new Purchase(lineas);
		
		cart.getPurchases().add(newPurchase);
		cart.refreshNumber();
		
		this.setCart(cart);
	}
	
	
}
