package com.example.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.controller.dao.services.PersonaService;
import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        Map<String, Object> mapa = new HashMap<>();
        PersonaService pd = new PersonaService(); 
        String aux = "";
        
        try {
            // Verificamos si la lista de personas está vacía
            aux = "La lista de personas está vacía: " + pd.listAll().isEmpty();

            // Crear y asignar datos a la Persona
            pd.getPersona().setNombre("Admiddddddn");
            pd.getPersona().setApellido("Admin");
            pd.getPersona().setActivo(false);
            pd.getPersona().setCelular("1234567890");

            // Convertir la fecha de String a Date
            String fechaNacimientoStr = "1990-01-01";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            pd.getPersona().setFechaNacimiento(fechaNacimiento);

            // Convertir el String "MASCULINO" a un valor de enum Genero
            pd.getPersona().setGenero(Enum.valueOf(Genero.class, "MASCULINO"));

            pd.getPersona().setIdentificacion("1234567890");

            // Convertir el String "CEDULA" a un valor de enum TipoIdentificacion
            pd.getPersona().setTipo(Enum.valueOf(TipoIdentificacion.class, "CEDULA"));

            // Guardar la persona
            pd.save();

        } catch (Exception e) {
            System.out.println("Error al procesar: " + e.getMessage());
            mapa.put("msg", "Error");
            mapa.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }

        // Agregar la información al mapa de respuesta
        mapa.put("msg", "Ok");
        mapa.put("data", "Test: " + aux);

        // Construir y devolver la respuesta
        return Response.ok(mapa).build();
    }
}
