package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controller.dao.services.LoginService;

@Path("/login")
public class LoginApi {
	
	@Path("/auth")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(HashMap requ) throws Exception{
		//1. Llamo los servicios
		LoginService logService = new LoginService();
		String mensaje;
		
		//2 Construir response 
		HashMap map = new HashMap<>();
		
		try {			
			String email = requ.get("correo").toString();
			String pwd = requ.get("contrasenia").toString();
			
			System.out.println("email: "+email+", pwd: "+pwd);
			
			mensaje = logService.login(email, pwd);
			
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = "Ocurrio un error "+e.getCause();
			map.put("error Localized", e.getLocalizedMessage());
			map.put("error Mesag", e.getMessage());
		}
		
		map.put("msg", "tester");
		map.put("data", mensaje);
		
		return Response.ok(map).build();
		
		
	}
}
