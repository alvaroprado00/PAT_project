package com.myProject.estanco.model;

import java.util.List;

import lombok.Data;

/**
 * 
 * Clase para manejar los usuarios que van a comprar
 *
 */

@Data
public class UserPurchase {
	
	private String userName;
	private List<LineaCompra> lineasCompra;
	
}
