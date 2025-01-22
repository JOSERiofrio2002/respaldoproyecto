package com.example.rest;
import com.example.controller.dao.services.PersonaService;
import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.google.gson.Gson;

@Path("person")
public class PersonaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        PersonaService ps = new PersonaService();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        PersonaService ps = new PersonaService();
        try {
            ps.setPersona(ps.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", ps.getPersona());

        if (ps.getPersona() == null || ps.getPersona().getId() == 0) {
            map.put("msg", "No se encontró persona con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

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
            PersonaService ps = new PersonaService();
            ps.getPersona().setNombre(map.get("nombre").toString());
            ps.getPersona().setApellido(map.get("apellido").toString());
            ps.getPersona().setCelular(map.get("celular").toString());
            ps.getPersona().setIdentificacion(map.get("identificacion").toString());
            ps.getPersona().setActivo(false);

            // Convertir la fecha de nacimiento de String a Date
            String fechaNacimientoStr = map.get("fechaNacimiento").toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            ps.getPersona().setFechaNacimiento(fechaNacimiento);

            // Asignar enumerados de Genero y TipoIdentificacion
            ps.getPersona().setGenero(Enum.valueOf(Genero.class, map.get("genero").toString()));
            ps.getPersona().setTipo(Enum.valueOf(TipoIdentificacion.class, map.get("tipo").toString()));
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

        // todo
        // Validation

    }

    @Path("/listType")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        PersonaService ps = new PersonaService();
        map.put("msg", "Ok");
        map.put("data", ps.getPersona());
        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();
        

        try {
            PersonaService ps = new PersonaService();
            ps.setPersona(ps.get(Integer.parseInt(map.get("idRol").toString())));
            ps.getPersona().setNombre(map.get("nombre").toString());
            ps.getPersona().setApellido(map.get("apellido").toString());
            ps.getPersona().setCelular(map.get("celular").toString());
            ps.getPersona().setIdentificacion(map.get("identificacion").toString());
            ps.getPersona().setActivo(false);

            // Convertir la fecha de nacimiento de String a Date
            String fechaNacimientoStr = map.get("fechaNacimiento").toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            ps.getPersona().setFechaNacimiento(fechaNacimiento);

            // Asignar enumerados de Genero y TipoIdentificacion
            ps.getPersona().setGenero(Enum.valueOf(Genero.class, map.get("genero").toString()));
            ps.getPersona().setTipo(Enum.valueOf(TipoIdentificacion.class, map.get("tipo").toString()));

            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

        // todo
        // Validation

    }
    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();

    try {
        PersonaService ps = new PersonaService();
        Integer id = Integer.parseInt(map.get("idPersona").toString());
        
        Boolean success = ps.delete(id);
        if (success) {
            res.put("msg", "Ok");
            res.put("data", "Eliminado correctamente");
            return Response.ok(res).build();
        } else {
            res.put("msg", "Error");
            res.put("data", "Persona no encontrada");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }
    } catch (Exception e) {
        System.out.println("Error en delete data" + e.toString());
        res.put("msg", "Error");
        res.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}


}
