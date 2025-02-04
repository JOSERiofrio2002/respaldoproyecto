package com.example.rest;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.CampeonatoServices;
import com.example.models.enumerador.TipoCategoria;
import com.google.gson.Gson;


@Path("campeonato")
public class CampeonatoApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        CampeonatoServices jes = new CampeonatoServices();
        map.put("msg", "Ok");
        map.put("data", jes.listAll().toArray());
        if (jes.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        CampeonatoServices jes = new CampeonatoServices();
        try {
            jes.setCampeonato(jes.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", jes.getCampeonato());

        if (jes.getCampeonato() == null || jes.getCampeonato().getId() == 0) {
            map.put("msg", "No se encontró persona con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (jes.listAll().isEmpty()) {
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
        CampeonatoServices jes = new CampeonatoServices();

        // Validar y asignar 'FechaInicio'
        if (map.get("fechaInicio") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'fechaInicio' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setFechaInicio(map.get("fechaInicio").toString());

        // Validar y asignar 'FechaFin'
        if (map.get("fechaFin") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'fechaFin' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setFechaFin(map.get("fechaFin").toString());

        // Validar y asignar 'Nombre'
        if (map.get("nombre") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'nombre' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setNombre(map.get("nombre").toString());

        // Validar y asignar 'Categoria'
        if (map.get("categoria") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'categoria' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        String categoriaStr = map.get("categoria").toString();
        try {
            TipoCategoria categoria = TipoCategoria.valueOf(categoriaStr.toUpperCase());
            jes.getCampeonato().setCategoria(categoria);
        } catch (IllegalArgumentException e) {
            res.put("msg", "Error");
            res.put("data", "El valor de 'Categoria' no es válido. Valores permitidos: MASCULINO, FEMENINA.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        // Guardar el campeonato
        jes.save();

        res.put("msg", "Ok");
        res.put("data", "Campeonato guardado correctamente.");
        return Response.ok(res).build();

    } catch (Exception e) {
        System.out.println("Error en save data: " + e.toString());
        res.put("msg", "Error");
        res.put("data", "Error inesperado: " + e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}


@Path("/update")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        CampeonatoServices jes = new CampeonatoServices();

        // Validar y asignar 'FechaInicio'
        if (map.get("fechaInicio") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'fechaInicio' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setFechaInicio(map.get("fechaInicio").toString());

        // Validar y asignar 'FechaFin'
        if (map.get("fechaFin") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'fechaFin' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setFechaFin(map.get("fechaFin").toString());

        // Validar y asignar 'Nombre'
        if (map.get("nombre") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'nombre' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }
        jes.getCampeonato().setNombre(map.get("nombre").toString());

        // Validar y asignar 'Categoria'
        if (map.get("categoria") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'categoria' es requerido.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        String categoriaStr = map.get("categoria").toString();
        try {
            TipoCategoria categoria = TipoCategoria.valueOf(categoriaStr.toUpperCase());
            jes.getCampeonato().setCategoria(categoria);
        } catch (IllegalArgumentException e) {
            res.put("msg", "Error");
            res.put("data", "El valor de 'categoria' no es válido. Valores permitidos: MASCULINO, FEMENINA.");
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        }

        // Llamar al método de actualización del servicio
        jes.update();

        // Respuesta de éxito
        res.put("msg", "Ok");
        res.put("data", "Guardado correctamente");
        return Response.ok(res).build();

    } catch (Exception e) {
        // Manejo de errores generales
        System.out.println("Error en update data: " + e.toString());
        res.put("msg", "Error");
        res.put("data", "Error inesperado: " + e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

 @Path("/delete/{id}")
@DELETE  // Cambié el método a DELETE
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@PathParam("id") Integer id) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        CampeonatoServices js = new CampeonatoServices();

        // Llamamos al servicio para eliminar la inscripción con el id proporcionado
        Boolean success = js.delete(id);

        if (success) {
            res.put("msg", "Ok");
            res.put("data", "Eliminado correctamente");
            return Response.ok(res).build();
        } else {
            res.put("msg", "Error");
            res.put("data", "Inscripción no encontrada");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }
    } catch (Exception e) {
        System.out.println("Error en delete data: " + e.toString());
        res.put("msg", "Error");
        res.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

}
