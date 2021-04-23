package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("GIFS")
public class GIF {
	
	//La anotation @Id indica que atributo actua de primary key en la tabla
	@Id
	private long id;
	
	//La anotation @Column permite asegurar el mapeo y ponerle el nombre que quieres a esa columna en la tabla
	@Column("NOMBRE")
	private String nombre;
	private String contenido;
}
