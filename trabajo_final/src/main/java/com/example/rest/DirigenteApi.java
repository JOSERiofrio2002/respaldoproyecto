package com.example.rest;

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

import com.example.controller.dao.services.DirigenteServices;
import com.google.gson.Gson;

@Path("dirigente")
public class DirigenteApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        DirigenteServices ds = new DirigenteServices();
        map.put("msg", "Ok");
        map.put("data", ds.listAll().toArray());
        if (ds.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        DirigenteServices ds = new DirigenteServices();
        try {
            ds.setDirigente(ds.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", ds.getDirigente());

        if (ds.getDirigente() == null || ds.getDirigente().getId() == 0) {
            map.put("msg", "No se encontró persona con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (ds.listAll().isEmpty()) {
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
            DirigenteServices ds = new DirigenteServices();
            ds.getDirigente().setNombre(map.get("nombre").toString());
            ds.getDirigente().setApellido(map.get("apellido").toString());
            ds.getDirigente().setTipo(ds.getTipoIdentificacion(map.get("tipo").toString()));
            ds.getDirigente().setIdentificacion(map.get("identificacion").toString());
            ds.getDirigente().setCelular(map.get("celular").toString());
            ds.getDirigente().setGenero(ds.getTipoGenero(map.get("genero").toString()));
            // ds.getDirigente().setFechaNacimiento(new
            // Date(map.get("fechaNacimiento").toString()));
            ds.getDirigente().setAniosExperiencia(Integer.parseInt(map.get("aniosExperiencia").toString()));
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                ds.getDirigente().setFechaNacimiento(fechaNacimiento);
            }
            ds.save();
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
        DirigenteServices ds = new DirigenteServices();
        map.put("msg", "Ok");
        map.put("data", ds.getTipos());
        if (ds.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/listTypeGenero")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypeGenero() {
        HashMap map = new HashMap<>();
        DirigenteServices ds = new DirigenteServices();
        map.put("msg", "Ok");
        map.put("data", ds.getGenero());
        if (ds.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            DirigenteServices ds = new DirigenteServices();
            ds.setDirigente(ds.get(Integer.parseInt(map.get("idDirigente").toString())));
            ds.getDirigente().setNombre(map.get("nombre").toString());
            ds.getDirigente().setApellido(map.get("apellido").toString());
            ds.getDirigente().setTipo(ds.getTipoIdentificacion(map.get("tipo").toString()));
            ds.getDirigente().setIdentificacion(map.get("identificacion").toString());
            ds.getDirigente().setAniosExperiencia(Integer.parseInt(map.get("aniosExperiencia").toString()));
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                ds.getDirigente().setFechaNacimiento(fechaNacimiento);
            }
            ds.update();
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

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();

        try {
            DirigenteServices ds = new DirigenteServices();
            Integer id = Integer.parseInt(map.get("idDirigente").toString());

            Boolean success = ds.delete(id);
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
