package com.example.rest;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.controller.dao.services.ReglamentoServices;
import com.google.gson.Gson;
import com.example.models.enumerador.Formato; // Add this import statement


@Path("reglamento")
public class ReglamentoApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        ReglamentoServices js = new ReglamentoServices();
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
        ReglamentoServices js = new ReglamentoServices();
        try {
            js.setReglamento(js.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", js.getReglamento());

        if (js.listAll().isEmpty()) {
            map.put("data", new Object[] {});
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

   

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        ReglamentoServices js = new ReglamentoServices();
        map.put("msg", "Ok");
        map.put("data", js.getReglamento());
        if (js.listAll().isEmpty()) {
            map.put("data", new Object[] {});
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
    
    // Convertimos el objeto HashMap a JSON para depuración
    String a = g.toJson(map);
    System.out.println("***** " + a);

    try {
        // Validaciones
        if (!map.containsKey("nombreReglamento") || map.get("nombreReglamento") == null || map.get("nombreReglamento").toString().isEmpty()) {
            res.put("msg", "Error");
            res.put("data", "El campo 'nombreReglamento' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        if (!map.containsKey("Descripcion") || map.get("Descripcion") == null || map.get("Descripcion").toString().isEmpty()) {
            res.put("msg", "Error");
            res.put("data", "El campo 'Descripcion' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

       

        if (!map.containsKey("formato") || map.get("formato") == null || map.get("formato").toString().isEmpty()) {
            res.put("msg", "Error");
            res.put("data", "El campo 'formato' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Casteo del campo formato
        Formato formato = Formato.valueOf(map.get("formato").toString().toUpperCase());
        
        // Llamada al servicio para guardar
        ReglamentoServices ps = new ReglamentoServices();
        ps.getReglamento().setNombreReglamento(map.get("nombreReglamento").toString());
        ps.getReglamento().setDescripcion(map.get("Descripcion").toString());
        
        ps.getReglamento().setFormato(formato);

        // Guardado en la base de datos o servicio
        ps.save();

        // Respuesta exitosa
        res.put("msg", "Ok");
        res.put("data", "Guardado correctamente");
        return Response.ok(res).build();

    } catch (IllegalArgumentException e) {
        // Excepción cuando el formato no es válido
        res.put("msg", "Error");
        res.put("data", "El valor de 'formato' no es válido.");
        return Response.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
        // Manejo general de errores
        System.out.println("Error en save data: " + e.toString());
        res.put("msg", "Error");
        res.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

@Path("/update")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap map) {
    HashMap<String, Object> res = new HashMap<>();
    
    try {
        // Validar si el 'id' está presente en el mapa
        if (!map.containsKey("id") || map.get("id") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'id' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar si el 'nombreReglamento' está presente
        if (!map.containsKey("nombreReglamento") || map.get("nombreReglamento") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'nombreReglamento' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar si el 'descripcion' está presente
        if (!map.containsKey("Descripcion") || map.get("Descripcion") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'Descripcion' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar si el 'formato' está presente
        if (!map.containsKey("formato") || map.get("formato") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'formato' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar si el 'formato' es un valor válido
        String formatoStr = map.get("formato").toString().toUpperCase();
        Formato formato;
        try {
            formato = Formato.valueOf(formatoStr);
        } catch (IllegalArgumentException e) {
            res.put("msg", "Error");
            res.put("data", "El valor de 'formato' no es válido. Valores posibles: ELIMINACION, TODOS_CONTRA_TODOS, GRUPOS.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Obtener el servicio y el reglamento correspondiente
        ReglamentoServices ps = new ReglamentoServices();
        Integer id = Integer.parseInt(map.get("id").toString());
        ps.setReglamento(ps.get(id));

        // Verificar si se encontró el reglamento
        if (ps.getReglamento() == null) {
            res.put("msg", "Error");
            res.put("data", "El reglamento con el ID proporcionado no existe.");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }

        // Actualizar los campos
        ps.getReglamento().setNombreReglamento(map.get("nombreReglamento").toString());
        ps.getReglamento().setDescripcion(map.get("Descripcion").toString());
        ps.getReglamento().setFormato(formato);

        // Respuesta exitosa
        res.put("msg", "Ok");
        res.put("data", "Reglamento actualizado correctamente.");
        return Response.ok(res).build();

    } catch (NumberFormatException e) {
        res.put("msg", "Error");
        res.put("data", "El ID proporcionado no es válido.");
        return Response.status(Status.BAD_REQUEST).entity(res).build();
    } catch (Exception e) {
        System.out.println("Error en update data: " + e.toString());
        res.put("msg", "Error");
        res.put("data", e.toString());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

    @Path("/delete/{id}")
@DELETE  // Cambié el método a DELETE
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@PathParam("id") Integer id) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        ReglamentoServices js = new ReglamentoServices();

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
