package com.example.controller.configuration;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Clase que permite filtrar los urls, de modo que sean seguros
 * siempre y cuando el usuario esta logueado..
 */
public class FiltroIntercepter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String url = requestContext.getUriInfo().getAbsolutePath().toString();
		HashMap map = new HashMap<>();

		if (url.contains("/login/auth")) { // Es decir se esta logueando, no pasa el filtro aun
			return;
		}

		// Si se ha logueado, debe de existir un token en el header que envia por response
		String token = requestContext.getHeaderString("Authorization");

		if (token.length() <= 7) { // Bearer -> por defecto tiene el bearer en el value, asi que si existe un token la logitud debe ser mayor a 7
			// Existe un token invalido
			map.put("msg", "Debe autenticarse, acceso no autorizado.");
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity(map).build());
		}

		// Debe hacerse la validacion del token en cuanto a la expiracion de token..

	}

}
