package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("TABACOINDUSTRIAL")
@NoArgsConstructor
@ToString
public class TabacoIndustrial extends Article{
	
	//La anotation @Id indica que atributo actua de primary key en la tabla
	@Id
	private long id;
	
	//El resto de campos para la tabla los coge del padre Article o como?

	//El resto de getters y setter vienen de article
	@Getter
	@Setter
	private int cigarrillos;
	
}
