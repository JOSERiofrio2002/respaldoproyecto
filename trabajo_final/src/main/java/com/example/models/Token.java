package com.example.models;

import java.time.LocalDateTime;

/**
 * Clase que describe atributos esenciales para brindar seguridad a la aplicacion
 * 
 */
public class Token {
	
	private int idToken, idUsr;
	
	private String token;
	private LocalDateTime fecha_creacion;
	// variable de refresh token	
	private LocalDateTime expiracion_token;
	private boolean isValid;
	
	public Token() {
		
	}
	
	public Token(String token, LocalDateTime fecha_creacion, LocalDateTime fecha_expiracion) {
		this.expiracion_token = fecha_expiracion;
		this.fecha_creacion = fecha_creacion;
		this.token = token;
		isValid = true; //Ojo Aqui
	}
	
	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
	
	public int getIdToken() {
		return idToken;
	}
	public void setIdToken(int idToken) {
		this.idToken = idToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDateTime fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public LocalDateTime getExpiracion_token() {
		return expiracion_token;
	}
	public void setExpiracion_token(LocalDateTime expiracion_token) {
		this.expiracion_token = expiracion_token;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
