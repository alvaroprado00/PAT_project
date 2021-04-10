package com.myProject.estanco.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase {
	
	static String pattern = "MM/dd/yyyy HH:mm:ss";
	static DateFormat df = new SimpleDateFormat(pattern);
	
	public Purchase(List<LineaCompra> lineasCompra) {
		
		this.lineasCompra = lineasCompra;
		this.date = df.format(Calendar.getInstance().getTime());
		this.idPurchase= "Purchase"+String.valueOf(Calendar.getInstance().getTime().getMinutes())+String.valueOf(lineasCompra.size());

	}

	private List<LineaCompra> lineasCompra;
	private String idPurchase;
	private String date;

	
}
