package com.myProject.estanco.model;

import java.util.List;

import lombok.Data;

/**
 * 
 * Clase para manejar los usuarios que van a acomprar
 *
 */

@Data
public class UserPurchase {
	
	private String nameUser;
	private List<LineaCompra> lineasCompra;
	
}
