package com.myProject.estanco.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
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
	
	public Purchase(Set<LineaCompra> lineasCompra,UserPurchaseInfo user) {
		
		this.lineasCompra= new HashSet<>();
		lineasCompra.addAll(lineasCompra);
		this.date = df.format(Calendar.getInstance().getTime());
		this.idPurchase= "Purchase"+String.valueOf(Calendar.getInstance().getTime().getMinutes())+String.valueOf(lineasCompra.size());
		this.userPurchase = user;
	}
	
	@Id
	@Column("id_purchase")
	private String idPurchase;
	
	@Column("date_purchase")
	private String date;
	
	@Embedded(onEmpty = OnEmpty.USE_EMPTY)
	private UserPurchaseInfo userPurchase;

	@MappedCollection(idColumn="id_purchase")
	private Set<LineaCompra> lineasCompra;

}
