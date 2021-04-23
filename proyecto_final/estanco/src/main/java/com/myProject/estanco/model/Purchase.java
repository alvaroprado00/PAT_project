package com.myProject.estanco.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

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
		
		this.lineasCompra= new HashSet<>();
		lineasCompra.addAll(lineasCompra);
		this.date = df.format(Calendar.getInstance().getTime());
		this.idPurchase= "Purchase"+String.valueOf(Calendar.getInstance().getTime().getMinutes())+String.valueOf(lineasCompra.size());

	}
	
	@Id
	@Column("id_purchase")
	private String idPurchase;
	
	@Column("date")
	private String date;

	@MappedCollection(idColumn="id_purchase")
	private Set<LineaCompra> lineasCompra;
}
