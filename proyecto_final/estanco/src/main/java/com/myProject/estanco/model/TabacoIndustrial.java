package com.myProject.estanco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@ToString
public class TabacoIndustrial extends Article{

	//El resto de getters y setter vienen de article
	@Getter
	@Setter
	private int cigarrillos;
	
}
