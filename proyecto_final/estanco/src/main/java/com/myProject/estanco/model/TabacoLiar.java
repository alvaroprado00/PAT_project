package com.myProject.estanco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class TabacoLiar extends Article {

	@Getter
	@Setter
	private int gramos;
}
