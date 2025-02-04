package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.LoginService;
import com.example.controller.dao.services.PersonaService;
import com.example.controller.dao.services.TokenService;
import com.example.controller.dao.services.UsuarioService;
import com.example.models.Persona;
import com.example.models.Token;

@Path("/login")
public class LoginApi {
	
	@Path("/auth")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap requ) throws Exception{
		//1. Llamo los servicios
		LoginService logService = new LoginService();
		UsuarioService userService = new UsuarioService();
		// Servicio para traer el objeto persona por ID
		PersonaService personaService = new PersonaService();

		Persona persona;
		String mensaje;
		
		//2 Construir response 
		HashMap map = new HashMap<>();
		
		String email = requ.get("correo").toString();
		try {			
			String pwd = requ.get("contrasenia").toString();
			
//			System.out.println("email: "+email+", pwd: "+pwd);
			
			mensaje = logService.login(email, pwd);
			
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = "Ocurrio un error "+e.getCause();
			map.put("error Localized", e.getLocalizedMessage());
			map.put("error Mesag", e.getMessage());
		}
		
		if(mensaje.compareToIgnoreCase("Usuario no existe") == 0 ||
				mensaje.compareToIgnoreCase("Contraseña incorrecta") == 0) {
			map.put("msg", "Error, credenciales incorrectas o el usuario no existe..");
			return Response.status(Status.UNAUTHORIZED).entity(map).build();
		}else {
			map.put("msg", "OK");
			// Se debe de construir un Response Builder
			map.put("data_tokn", mensaje);
			persona = personaService.get(userService.findUserbyEmail(email).getIdPersona());
			map.put("persona", persona.getApellido()+" "+persona.getNombre());
			return Response.status(Status.OK).entity(map).build();
		}
		
		
	}
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(HashMap request) throws Exception {
		// 1. Llamar Services
		LoginService logService = new LoginService();
		TokenService tokService = new TokenService();

		//2. Construir map para response
		HashMap map = new HashMap<>();

		// Captura el tokn que envia desde front
		String tkn = request.get("tokn").toString();
		
		// busca el tokn		
		Token tokn = tokService.findbyToken(tkn);


		if(logService.logout(tokn.getIdToken())) {
			// Buscar en base de datos
			// Eliminar de la base de datos
			// Eliminacion correcta
			map.put("msg", "OK");
			map.put("data", "Inicio de sesion cerrada exitosamente");
			return Response.status(Status.OK).entity(map).build();
		}else {
			map.put("msg", "Error");
			map.put("data", "Algo salio mal ..!");
			return Response.status(Status.EXPECTATION_FAILED).entity(map).build();
		}

//		return null;
	}
}
