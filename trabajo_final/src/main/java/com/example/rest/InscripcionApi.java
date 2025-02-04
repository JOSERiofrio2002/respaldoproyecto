package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;
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

import com.example.controller.dao.services.InscripcionService;
import com.example.models.Inscripcion;

@Path("Inscripcion")
public class InscripcionApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        InscripcionService js = new InscripcionService();
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
        InscripcionService js = new InscripcionService();
        try {
            js.setInscripcion(js.get(id));
        } catch (Exception e) {

        }

        map.put("msg", "Ok");
        map.put("data", js.getInscripcion());

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
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        try {
            // Validar que el campo requerido esté presente
            if (!map.containsKey("valorInscripcion") || map.get("valorInscripcion") == null) {
                res.put("msg", "Error");
                res.put("data", "El campo 'valorInscripcion' es obligatorio.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }
    
            // Convertir el valor
            Double valorInscripcion;
            try {
                valorInscripcion = Double.parseDouble(map.get("valorInscripcion").toString());
            } catch (NumberFormatException e) {
                res.put("msg", "Error");
                res.put("data", "El valor de inscripción no es válido.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }
    
            // Guardar en el servicio
            InscripcionService js = new InscripcionService();
            if (js.getInscripcion() == null) {
                js.setInscripcion(new Inscripcion());
            }
            js.getInscripcion().setValorInscripcion(valorInscripcion);
            js.save();
    
            // Respuesta exitosa
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();
    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en save data: " + e.getMessage());
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    


    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        InscripcionService js = new InscripcionService();
        map.put("msg", "Ok");
        map.put("data", js.getInscripcion());
        if (js.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }



    @Path("/update")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        // Validar campos requeridos
        if (!map.containsKey("id") || map.get("id") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'id' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        if (!map.containsKey("valorInscripcion") || map.get("valorInscripcion") == null) {
            res.put("msg", "Error");
            res.put("data", "El campo 'valorInscripcion' es obligatorio.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Convertir valores
        Integer id;
        Double valorInscripcion;

        try {
            id = Integer.parseInt(map.get("id").toString());
            valorInscripcion = Double.parseDouble(map.get("valorInscripcion").toString());
        } catch (NumberFormatException e) {
            res.put("msg", "Error");
            res.put("data", "Los valores de 'id' o 'valorInscripcion' no son válidos.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Inicializar servicio y buscar inscripción
        InscripcionService js = new InscripcionService();
        Inscripcion inscripcion = js.get(id);

        if (inscripcion == null) {
            res.put("msg", "Error");
            res.put("data", "La inscripción con el ID proporcionado no existe.");
            return Response.status(Status.NOT_FOUND).entity(res).build();
        }

        // Actualizar inscripción
        inscripcion.setValorInscripcion(valorInscripcion);
        js.setInscripcion(inscripcion);
        js.update();

        // Respuesta exitosa
        res.put("msg", "Ok");
        res.put("data", "Actualizado correctamente.");
        return Response.ok(res).build();

    } catch (Exception e) {
        e.printStackTrace();
        res.put("msg", "Error");
        res.put("data", e.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }
}

    @Path("/delete/{id}")
@DELETE  // Cambié el método a DELETE
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@PathParam("id") Integer id) {
    HashMap<String, Object> res = new HashMap<>();

    try {
        InscripcionService js = new InscripcionService();

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
