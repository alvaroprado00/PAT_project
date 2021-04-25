package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("TABACOLIAR")
@NoArgsConstructor
@ToString
public class TabacoLiar extends Article {

	@Id
	private long id;
	
	//El resto de campos para la tabla los coge del padre Article o como
	
	@Getter
	@Setter
	private int gramos;

}
