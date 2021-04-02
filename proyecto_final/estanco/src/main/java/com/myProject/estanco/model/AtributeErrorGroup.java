package com.myProject.estanco.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtributeErrorGroup {
	
	
	
	//Cuando una clas es muy pequeña nos la podemos nombrar dentro
	//Indico que quiero tener un constructor completo
	@Data
	@AllArgsConstructor
	public static class AtributeError{
		
		private String field;
		private String rejectedValue;
		private boolean bindingFailure;
	}
	
	private List<AtributeError> errors;
}

