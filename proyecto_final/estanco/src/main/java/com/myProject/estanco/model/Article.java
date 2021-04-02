package com.myProject.estanco.model;

import lombok.Getter;
import lombok.Setter;



//clase abstracta de la que heredan los productos que vamos a vender en el estanco

//No podemos usar @Data porque implicaria tener un constructor por defecto y es una clase abstracta

@Getter
@Setter
public abstract class Article {
	
	private String codigo;
	private String marca;
	private String descripcion;
	private String imagen;
	private float precio;
	

}
