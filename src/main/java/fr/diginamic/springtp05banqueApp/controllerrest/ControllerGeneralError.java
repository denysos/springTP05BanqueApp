package fr.diginamic.springtp05banqueApp.controllerrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.diginamic.springtp05banqueApp.exception.ClientNotFoundException;
import fr.diginamic.springtp05banqueApp.exception.CompteNotFoundException;


@RestControllerAdvice
public class ControllerGeneralError {
	
	@ExceptionHandler(value = { ClientNotFoundException.class })
	public ResponseEntity<String> onErrorClient(ClientNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClientError : " + ex.getMessage());
			}
	
	@ExceptionHandler(value = { CompteNotFoundException.class })
	public ResponseEntity<String> onErrorCompte(CompteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CompteError : " + ex.getMessage());
			}
	
	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<String> onError(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Other error : " + ex.getMessage());
	}
}
