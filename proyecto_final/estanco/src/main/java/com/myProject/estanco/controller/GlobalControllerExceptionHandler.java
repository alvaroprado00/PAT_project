package com.myProject.estanco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.myProject.estanco.model.AtributeErrorGroup;
import com.myProject.estanco.model.AtributeErrorGroup.AtributeError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
class GlobalControllerExceptionHandler {
	
	
	//Para el resto de exceptions por ahora muestro el mensaje mas bonito
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericError(Exception e) {
		
		log.warn("Está ocurriendo una exception generica: "+e.getMessage());
		ResponseEntity<String> response= new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<AtributeErrorGroup> handleArgumentExceptions(MethodArgumentNotValidException e){
		
		//Para mandar algo mas sencillo al front me creo un objeto tipo Atribute Error Group cuyo unico atributo sea una lista de Atribute Errors
		
		
		List<AtributeError> errorsCustom=new ArrayList<>();
		
		List<FieldError> errorsNoCustom=e.getFieldErrors();
		
		for (FieldError fe: errorsNoCustom) {
		
			//Yo tambien quiero ver que va pasando
			
			log.warn("Está ocurriendo una exception de tipo argumento no valido: "+fe.getDefaultMessage());
			
			String valorCausanteExcepcion;
			
			//En ocasiones el valor que causa la excepcion esta a null porque no se ha expecificado
			//En esos casos nos interesa indicarlo con un string
			try {
				valorCausanteExcepcion=fe.getRejectedValue().toString();
			}catch(NullPointerException npe) {
				valorCausanteExcepcion="null";
			}
			//Para cada field error me creo una version custom
			AtributeError errorCustom= new AtributeError(fe.getField(), valorCausanteExcepcion, fe.isBindingFailure());
			errorsCustom.add(errorCustom);
		}
		
		AtributeErrorGroup groupOfErrors = new AtributeErrorGroup(errorsCustom);
		
		ResponseEntity<AtributeErrorGroup> response= new ResponseEntity<>(groupOfErrors, HttpStatus.CONFLICT);
		
		return response;
	}
	
	
	/* Esta manera funciona pero devuelves un 400 y ya sin indicarle que fallos ha tenido en los argumentos rellenos
	 * 
	 * 
	 *  @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
	 *  @ExceptionHandler(MethodArgumentNotValidException.class)
	 *  public void handleConflict() {
	 *      //No se hace nada
	 *  	log.warn("Ha habido una bad request y la ha controlado mi exception handler");
	 *  }
    */
}