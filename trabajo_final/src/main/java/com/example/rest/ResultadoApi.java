package com.example.rest;

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

import com.example.controller.services.ResultadoServices;
import com.example.models.Encuentro;
import com.example.models.Resultado;

@Path("resultado")
public class ResultadoApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listShowAll() {
        HashMap<String, Object> res = new HashMap<>();
        try {
            ResultadoServices rs = new ResultadoServices();
            Object[] resultados = rs.listAll().toArray();
            res.put("msg", "Ok");
            res.put("data", resultados);
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResultado(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        ResultadoServices rs = new ResultadoServices();
        try {
            rs.setResultado(rs.get(id));
        } catch (Exception e) {
            // Handle exception
        }

        map.put("msg", "Ok");
        map.put("data", rs.getResultado());

        if (rs.getResultado() == null || rs.getResultado().getId() == 0) {
            map.put("msg", "No se encontró resultado con ese identificador");
            return Response.status(Status.NOT_FOUND).entity(map).build();
        }

        if (rs.listAll().isEmpty()) {
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
            ResultadoServices rs = new ResultadoServices();
            rs.getResultado().setEquipoGanador(map.get("equipoGanador").toString());
            rs.getResultado().setEquipoPerdedor(map.get("equipoPerdedor").toString());
            rs.getResultado().setGolesEquipo1(Integer.parseInt(map.get("golesEquipo1").toString()));
            rs.getResultado().setGolesEquipo2(Integer.parseInt(map.get("golesEquipo2").toString()));
            rs.getResultado().setEmpate(Boolean.parseBoolean(map.get("empate").toString()));
            rs.getResultado().setPuntosEncuentro(Integer.parseInt(map.get("puntosEncuentro").toString()));

            // Manejar encuentros
            Encuentro encuentro = new Encuentro();
            encuentro.setEquipo1(map.get("equipo1").toString());
            encuentro.setEquipo2(map.get("equipo2").toString());
            encuentro.setGolesEquipo1(Integer.parseInt(map.get("golesEquipo1").toString()));
            encuentro.setGolesEquipo2(Integer.parseInt(map.get("golesEquipo2").toString()));
            encuentro.setEmpate(Boolean.parseBoolean(map.get("empate").toString()));

            rs.save();
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
            ResultadoServices rs = new ResultadoServices();
            rs.getResultado().setEquipoGanador(map.get("equipoGanador").toString());
            rs.getResultado().setEquipoPerdedor(map.get("equipoPerdedor").toString());
            rs.getResultado().setGolesEquipo1(Integer.parseInt(map.get("golesEquipo1").toString()));
            rs.getResultado().setGolesEquipo2(Integer.parseInt(map.get("golesEquipo2").toString()));
            rs.getResultado().setEmpate(Boolean.parseBoolean(map.get("empate").toString()));
            rs.getResultado().setPuntosEncuentro(Integer.parseInt(map.get("puntosEncuentro").toString()));

            // Manejar encuentros
            Encuentro encuentro = new Encuentro();
            encuentro.setEquipo1(map.get("equipo1").toString());
            encuentro.setEquipo2(map.get("equipo2").toString());
            encuentro.setGolesEquipo1(Integer.parseInt(map.get("golesEquipo1").toString()));
            encuentro.setGolesEquipo2(Integer.parseInt(map.get("golesEquipo2").toString()));
            encuentro.setEmpate(Boolean.parseBoolean(map.get("empate").toString()));
            
            rs.save();
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
            ResultadoServices rs = new ResultadoServices();
            Integer id = Integer.parseInt(map.get("idResultado").toString());

            Boolean success = rs.delete(id);
            if (success) {
                res.put("msg", "Ok");
                res.put("data", "Eliminado correctamente");
                return Response.ok(res).build();
            } else {
                res.put("msg", "Error");
                res.put("data", "Resultado no encontrado");
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
    public Response ordenarResultados(
        @QueryParam("by") String orderBy,
        @QueryParam("direction") String orderDirection
    ) {
        HashMap<String, Object> map = new HashMap<>();
        ResultadoServices rs = new ResultadoServices();
        
        boolean ascendente = orderDirection == null || orderDirection.equalsIgnoreCase("asc");
        com.example.controller.tda.list.LinkedList<Resultado> resultados;

        try {
            switch (orderBy.toLowerCase()) {
                case "equipoganador":
                    resultados = rs.ordenarPorEquipoGanador(ascendente);
                    break;
                case "equipoperdedor":
                    resultados = rs.ordenarPorEquipoPerdedor(ascendente);
                    break;
                case "golesequipo1":
                    resultados = rs.ordenarPorGolesEquipo1(ascendente);
                    break;
                case "golesequipo2":
                    resultados = rs.ordenarPorGolesEquipo2(ascendente);
                    break;
                case "puntosencuentro":
                    resultados = rs.ordenarPorPuntosEncuentro(ascendente);
                    break;
                default:
                    map.put("msg", "Error");
                    map.put("data", "Criterio de ordenamiento no válido");
                    return Response.status(Status.BAD_REQUEST).entity(map).build();
            }

            map.put("msg", "Ok");
            map.put("data", resultados.toArray());
            map.put("total", resultados.getSize());
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
    public Response buscarResultados(
        @QueryParam("equipoGanador") String equipoGanador,
        @QueryParam("equipoPerdedor") String equipoPerdedor,
        @QueryParam("golesEquipo1") Integer golesEquipo1,
        @QueryParam("golesEquipo2") Integer golesEquipo2,
        @QueryParam("puntosEncuentro") Integer puntosEncuentro
    ) {
        HashMap<String, Object> map = new HashMap<>();
        ResultadoServices rs = new ResultadoServices();
        
        com.example.controller.tda.list.LinkedList<Resultado> resultados = rs.listAll();

        // Aplicar filtros
        if (equipoGanador != null) {
            resultados = rs.buscarPorEquipoGanador(equipoGanador);
        }
        if (equipoPerdedor != null) {
            resultados = rs.buscarPorEquipoPerdedor(equipoPerdedor);
        }
        if (golesEquipo1 != null) {
            resultados = rs.buscarPorGolesEquipo1(golesEquipo1);
        }
        if (golesEquipo2 != null) {
            resultados = rs.buscarPorGolesEquipo2(golesEquipo2);
        }
        if (puntosEncuentro != null) {
            resultados = rs.buscarPorPuntosEncuentro(puntosEncuentro);
        }

        map.put("msg", "Ok");
        map.put("data", resultados.toArray());
        map.put("total", resultados.getSize());

        return Response.ok(map).build();
    }


}