package com.myProject.estanco.model;

import java.util.List;

import lombok.Data;

@Data
public class Purchase {
	
	public Purchase(List<LineaCompra> lineaCompra) {
		lineasCompra = lineaCompra;
	}

	private List<LineaCompra> lineasCompra;

}
