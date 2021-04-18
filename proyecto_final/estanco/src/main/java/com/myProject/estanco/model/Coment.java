package com.myProject.estanco.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("coments")
public class Coment {

	//Con @column defino cual es el nombre que aparece en la table de mysql
	@Id
	@Column("coment_token")
	private String token;
	@Column("coment_content")
	private String content;
	
	@Column("positive_experience")
	private boolean positiveExperience;
	
}
