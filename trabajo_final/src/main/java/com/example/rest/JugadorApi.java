package com.example.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.JugadorServices;
import com.google.gson.Gson;

@Path("jugador")
public class JugadorApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        JugadorServices js = new JugadorServices();
        map.put("msg", "Ok");
        map.put("data", js.listAll().toArray());
        if (js.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        JugadorServices js = new JugadorServices();
        try {
            js.setJugador(js.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", js.getJugador());

        if (js.getJugador() == null || js.getJugador().getId() == 0) {
            map.put("msg", "No se encontró persona con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (js.listAll().isEmpty()) {
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
            JugadorServices js = new JugadorServices();
            js.getJugador().setNombre(map.get("nombre").toString());
            js.getJugador().setApellido(map.get("apellido").toString());
            js.getJugador().setTipo(js.getTipoIdentificacion(map.get("tipo").toString()));
            js.getJugador().setIdentificacion(map.get("identificacion").toString());
            js.getJugador().setCelular(map.get("celular").toString());
            js.getJugador().setGenero(js.getTipoGenero(map.get("genero").toString()));
            js.getJugador().setNumCamiseta(Integer.parseInt(map.get("numCamiseta").toString()));
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                js.getJugador().setFechaNacimiento(fechaNacimiento);
            }
            js.save();
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
        JugadorServices js = new JugadorServices();
        map.put("msg", "Ok");
        map.put("data", js.getTipos());
        if (js.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/listTypeGenero")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypeGenero() {
        HashMap map = new HashMap<>();
        JugadorServices js = new JugadorServices();
        map.put("msg", "Ok");
        map.put("data", js.getGenero());
        if (js.listAll().isEmpty()) {
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
            JugadorServices js = new JugadorServices();
            js.setJugador(js.get(Integer.parseInt(map.get("idJugador").toString())));
            js.getJugador().setNombre(map.get("nombre").toString());
            js.getJugador().setApellido(map.get("apellido").toString());
            js.getJugador().setTipo(js.getTipoIdentificacion(map.get("tipo").toString()));
            js.getJugador().setIdentificacion(map.get("identificacion").toString());
            js.getJugador().setCelular(map.get("celular").toString());
            js.getJugador().setGenero(js.getTipoGenero(map.get("genero").toString()));
            js.getJugador().setNumCamiseta(Integer.parseInt(map.get("numCamiseta").toString()));
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                js.getJugador().setFechaNacimiento(fechaNacimiento);
            }
            js.update();
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
            JugadorServices js = new JugadorServices();
            Integer id = Integer.parseInt(map.get("idJugador").toString());

            Boolean success = js.delete(id);
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
