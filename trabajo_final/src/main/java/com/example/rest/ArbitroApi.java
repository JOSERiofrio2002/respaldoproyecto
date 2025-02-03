package com.example.rest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.ArbitroServices;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Arbitro;
import com.example.models.enumerador.Genero;
import com.google.gson.Gson;

@Path("arbitro")
public class ArbitroApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArbitros() {
        HashMap<String, Object> map = new HashMap<>();
        ArbitroServices as = new ArbitroServices();
        map.put("msg", "Ok");
        map.put("data", as.listAll().toArray());
        if (as.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArbitro(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        ArbitroServices as = new ArbitroServices();
        try {
            as.setArbitro(as.get(id));
        } catch (Exception e) {
            // Handle exception
        }

        map.put("msg", "Ok");
        map.put("data", as.getArbitro());

        if (as.getArbitro() == null || as.getArbitro().getId() == 0) {
            map.put("msg", "No se encontró arbitro con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (as.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("***** " + a);

        try {
            ArbitroServices as = new ArbitroServices();
            as.getArbitro().setNombre(map.get("nombre").toString());
            as.getArbitro().setApellido(map.get("apellido").toString());
            as.getArbitro().setIdentificacion(map.get("identificacion").toString());
            as.getArbitro().setCelular(map.get("celular").toString());
            as.getArbitro().setGenero(Genero.valueOf(map.get("genero").toString()));
            as.getArbitro().setAsociacion(map.get("asociacion").toString());
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat.parse(fechaNacimientoStr);
                Date fechaNacimiento = new Date(utilDate.getTime());
                as.getArbitro().setFechaNacimiento(fechaNacimiento);
            }
            as.save();
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

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();

        try {
            ArbitroServices as = new ArbitroServices();
            as.setArbitro(as.get(Integer.parseInt(map.get("idArbitro").toString())));
            as.getArbitro().setNombre(map.get("nombre").toString());
            as.getArbitro().setApellido(map.get("apellido").toString()); 
            as.getArbitro().setIdentificacion(map.get("identificacion").toString());
            as.getArbitro().setCelular(map.get("celular").toString());
            as.getArbitro().setGenero(Genero.valueOf(map.get("genero").toString()));
            as.getArbitro().setAsociacion(map.get("asociacion").toString());
            if (map.containsKey("fechaNacimiento") && map.get("fechaNacimiento") != null) {
                String fechaNacimientoStr = map.get("fechaNacimiento").toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat.parse(fechaNacimientoStr);
                Date fechaNacimiento = new Date(utilDate.getTime());
                as.getArbitro().setFechaNacimiento(fechaNacimiento);
            }
            as.update();
            res.put("msg", "Ok");
            res.put("data", "Actualizado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en update data" + e.toString());
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
            ArbitroServices as = new ArbitroServices();
            Integer id = Integer.parseInt(map.get("idArbitro").toString());

            Boolean success = as.delete(id);
            if (success) {
                res.put("msg", "Ok");
                res.put("data", "Eliminado correctamente");
                return Response.ok(res).build();
            } else {
                res.put("msg", "Error");
                res.put("data", "Arbitro no encontrado");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            System.out.println("Error en delete data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/ordenar")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response ordenarArbitros(
    @QueryParam("by") String orderBy,
    @QueryParam("direction") String orderDirection
) {
    HashMap<String, Object> map = new HashMap<>();
    ArbitroServices as = new ArbitroServices();
    
    boolean ascendente = orderDirection == null || orderDirection.equalsIgnoreCase("asc");
    LinkedList<Arbitro> arbitros;

    try {
        switch (orderBy.toLowerCase()) {
            case "nombre":
                arbitros = as.ordenarPorNombre(ascendente);
                break;
            case "apellido":
                arbitros = as.ordenarPorApellido(ascendente);
                break;
            case "identificacion":
                arbitros = as.ordenarPorIdentificacion(ascendente);
                break;
            case "fechaNacimiento":
                arbitros = as.ordenarPorFechaNacimiento(ascendente);
                break;
            case "asociacion":
                arbitros = as.ordenarPorAsociacion(ascendente);
                break;
            default:
                map.put("msg", "Error");
                map.put("data", "Criterio de ordenamiento no válido");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "Ok");
        map.put("data", arbitros.toArray());
        map.put("total", arbitros.getSize());
    } catch (Exception e) {
        map.put("msg", "Error");
        map.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
    }

    return Response.ok(map).build();
}

@Path("/buscar")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response buscarArbitros(
    @QueryParam("nombre") String nombre,
    @QueryParam("apellido") String apellido,
    @QueryParam("identificacion") String identificacion,
    @QueryParam("asociacion") String asociacion,
    @QueryParam("genero") String genero
) {
    HashMap<String, Object> map = new HashMap<>();
    ArbitroServices as = new ArbitroServices();
    
    LinkedList<Arbitro> resultados = as.listAll();

    // Aplicar filtros
    if (nombre != null) {
        resultados = as.buscarPorNombre(nombre);
    }
    if (apellido != null) {
        resultados = as.buscarPorApellido(apellido);
    }
    if (identificacion != null) {
        resultados = as.buscarPorIdentificacion(identificacion);
    }
    if (asociacion != null) {
        resultados = as.buscarPorAsociacion(asociacion);
    }
    if (genero != null) {
        resultados = as.buscarPorGenero(genero);
    }

    map.put("msg", "Ok");
    map.put("data", resultados.toArray());
    map.put("total", resultados.getSize());

    return Response.ok(map).build();
}




}