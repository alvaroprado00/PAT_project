package com.myProject.estanco.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("purchases")
public class Purchase {
	
	static String pattern = "MM/dd/yyyy HH:mm:ss";
	static DateFormat df = new SimpleDateFormat(pattern);
	
	public Purchase(List<LineaCompra> lineasCompra) {
		
		this.lineasCompra = lineasCompra;
		this.date = df.format(Calendar.getInstance().getTime());
		this.idPurchase= "Purchase"+String.valueOf(Calendar.getInstance().getTime().getMinutes())+String.valueOf(lineasCompra.size());

	}
	
	@Id
	@Column("id_purchase")
	private String idPurchase;
	
	@Column("date")
	private String date;

	@MappedCollection(keyColumn="id_purchase", idColumn="id_purchase")
	private List<LineaCompra> lineasCompra;
}
