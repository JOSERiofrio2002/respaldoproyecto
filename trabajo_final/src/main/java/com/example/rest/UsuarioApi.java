package com.example.rest;

import javax.ws.rs.Path;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.UsuarioDao;
import com.example.controller.dao.services.PersonaService;
import com.example.controller.dao.services.UsuarioService;
import com.example.models.Arbitro;
import com.example.models.Jugador;
import com.example.models.Persona;
import com.example.models.Usuario;
import com.google.gson.Gson;

@Path("/usuario")
public class UsuarioApi {

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("***** " + a);

        try {
            UsuarioService ps = new UsuarioService();
            ps.getUsuario().setCorreo(map.get("correo").toString());
            ps.getUsuario().setContrasenia(map.get("contrasenia").toString());
            ps.getUsuario().setEstado( (Boolean) map.get("estado"));

            ps.save();
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        HashMap map = new HashMap<>();
        UsuarioService ps = new UsuarioService();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }
    
 // Asignar Usuario
    @Path("/asign/usr")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response asignarUsuario(HashMap map) {
        HashMap res = new HashMap<>();

        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("***** " + a);

        try {
            UsuarioService userService = new UsuarioService();
            PersonaService personaService = new PersonaService();

            // 1. verificar que el usuario exista en la bdd
            Usuario usr = userService.findUserbyEmail(map.get("correo").toString());

            int idPersona = (int) map.get("idPersona");

//            System.out.println("Usuario: "+usr.toString()+"\n");

            if (usr != null) { // existe el usuario
            	// 2. verificar que el idPersona exista en la bdd
            	userService.setUsuario(usr);
            	Object person = personaService.get(idPersona);

//            	System.out.println("Objeto: "+person.toString()+"\n");
            	if(person != null) {
            		// 3. verifica que la persona registrada no sea una instancia de Arbitro, Dirigente o Jugador
            		// Nota... Realmente no se esta validadndo si sea una instancia de Arbitro o Jugador, se debe usar isInstance(Persona)

            		if (!userService.existAsignacion()) { // No tiene una persona asignad

            			if (!userService.existOtherUser(idPersona)) { // Si existe otro registro con este idP

            				if (!(person instanceof Arbitro) || !(person instanceof Jugador)) {
            					// 4. capturar el id, y asignar el usuario (modificacion)
            					userService.getUsuario().setIdPersona(((Persona) person).getId());
            					userService.update();
            					res.put("msg", "Ok");
            					res.put("data", "Persona asignado un usuario correctamente");
            				}
						}else {
							res.put("msg", "Informacion");
							res.put("data", "Ya existe un usuario para la persona con ID "+idPersona);
						}

					}else {
						res.put("msg", "Informacion");
						res.put("data", "La persona con el identificador "+idPersona+", ya tiene asignado un usuario..");
					}

            	}
			}

            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data " + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            res.put("cause", e.getCause());
            res.put("LocalizedMessage", e.getLocalizedMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }
    
}
