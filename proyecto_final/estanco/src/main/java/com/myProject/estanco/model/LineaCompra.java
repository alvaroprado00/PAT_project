package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("lineas_compra")
public class LineaCompra {
	
	//Aqui creo que da igual como le llames en tu tabla de sql porque lo identifica al ser el ID
	@Id
	@Column("id_linea")
	private long id_linea;
	
	@Column("codigo")
	private String codigo;
	
	@Column("units")
	private int units;
	
	//@column permite el mapeo entre el atributo java y la column de la tabla 
	@Column("tipo_tabaco")
	private String tipoTabaco;

}
