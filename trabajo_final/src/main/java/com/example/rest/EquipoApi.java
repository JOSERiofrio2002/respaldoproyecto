package com.example.rest;

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

import com.example.controller.dao.services.EquipoServices;
import com.google.gson.Gson;

@Path("equipo")
public class EquipoApi {
        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllPersons() {
            HashMap map = new HashMap<>();
            EquipoServices es = new EquipoServices();
            map.put("msg", "Ok");
            map.put("data", es.listAll().toArray());
            if (es.listAll().isEmpty()) {
                map.put("data", new Object[] {});
            }
            return Response.ok(map).build();
        }
    
        @Path("/get/{id}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getPerson(@PathParam("id") Integer id) {
            HashMap map = new HashMap<>();
            EquipoServices es = new EquipoServices();
            try {
                es.setEquipo(es.get(id));
            } catch (Exception e) {
    
            }
    
            map.put("msg", "Ok");
            map.put("data", es.getEquipo());
    
            if (es.getEquipo() == null || es.getEquipo().getId() == 0) {
                map.put("msg", "No se encontró persona con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(map).build();
            }
    
            if (es.listAll().isEmpty()) {
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
                EquipoServices es = new EquipoServices();
                es.getEquipo().setNombre(map.get("nombre").toString());
                es.getEquipo().setIdInscripcion(Integer.parseInt(map.get("idInscripcion").toString()));

                es.save();
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
    
        @Path("/update")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response update(HashMap map) {
            HashMap res = new HashMap<>();
    
            try {
                EquipoServices es = new EquipoServices();
                es.setEquipo(es.get(Integer.parseInt(map.get("idEquipo").toString())));
                es.getEquipo().setNombre(map.get("nombre").toString());
                es.getEquipo().setIdInscripcion(Integer.parseInt(map.get("idInscripcion").toString()));
                
                es.update();
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
                EquipoServices es = new EquipoServices();
                Integer id = Integer.parseInt(map.get("idEquipo").toString());
    
                Boolean success = es.delete(id);
                if (success) {
                    res.put("msg", "Ok");
                    res.put("data", "Eliminado correctamente");
                    return Response.ok(res).build();
                } else {
                    res.put("msg", "Error");
                    res.put("data", "Dirigente no encontrado");
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
