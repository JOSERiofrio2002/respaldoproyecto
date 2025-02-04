package com.example.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.controller.dao.services.CampeonatoServices;
import com.example.controller.dao.services.PersonaService;
import com.example.models.Reglamento;
import com.example.models.Reglamento;
import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;
import com.example.models.enumerador.TipoCategoria;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        Map<String, Object> mapa = new HashMap<>();
        CampeonatoServices pd = new CampeonatoServices();
        String aux = "";

        // El JSON de reglamentos que recibes
        String jsonReglamentos = "[{\"id\": 1, \"nombreReglamento\": \"Pepes\", \"descripcion\": \"Reglamento de Pepes\", \"formato\": \"ELIMINACION\"}]";

        try {
            // Verificamos si la lista de reglamentos está vacía
            aux = "La lista de Calendario está vacía: " + pd.listAll().isEmpty();

            // Crear y asignar datos al campeonato
            pd.getCampeonato().setNombre("Nombre de prueba");
            pd.getCampeonato().setFechaInicio("2025-01-01");
            pd.getCampeonato().setFechaFin("2025-12-31");

            // Asignar una categoría válida del enum
            String categoriaStr = "MASCULINO"; // Puede cambiarse a "FEMENINA" según la necesidad
            try {
                TipoCategoria categoria = TipoCategoria.valueOf(categoriaStr.toUpperCase());
                pd.getCampeonato().setCategoria(categoria);
            } catch (IllegalArgumentException e) {
                throw new Exception("La categoría proporcionada no es válida. Valores permitidos: MASCULINO, FEMENINA.");
            }

            // Usar Jackson para convertir el JSON a objetos de tipo Reglamento
            ObjectMapper objectMapper = new ObjectMapper();
            List<Reglamento> reglamentos = objectMapper.readValue(jsonReglamentos, objectMapper.getTypeFactory().constructCollectionType(List.class, Reglamento.class));

            // Establecer la lista de reglamentos
            pd.getCampeonato().setReglamentos(reglamentos);

            // Guardar el reglamento
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
