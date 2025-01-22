package com.example.controller.dao.services;

import java.util.HashMap;

import com.example.controller.dao.ResultadoDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Encuentro;
import com.example.models.Resultado;

public class ResultadoServices {
    private ResultadoDao resultadoDao;
    private Resultado resultado;

    public Object[] listShowAll() throws Exception {
    if (!resultadoDao.getListAll().isEmpty()) {
        Resultado[] lista = (Resultado[]) resultadoDao.getListAll().toArray();
        Object[] respuesta = new Object[lista.length];
        for (int i = 0; i < lista.length; i++) {
            Encuentro e = new EncuentroServices().get(((Encuentro) lista[i].getEncuentro()).getId());
            HashMap<String, Object> mapa = new HashMap<>();
            mapa.put("id", lista[i].getId());
            mapa.put("equipoGanador", lista[i].getEquipoGanador());
            mapa.put("equipoPerdedor", lista[i].getEquipoPerdedor());
            mapa.put("golesEquipo1", lista[i].getGolesEquipo1());
            mapa.put("golesEquipo2", lista[i].getGolesEquipo2());
            mapa.put("empate", lista[i].getEmpate());
            mapa.put("puntosEncuentro", lista[i].getPuntosEncuentro());
            mapa.put("encuentro", e);
            respuesta[i] = mapa;
        }
        return respuesta;
    }
    return new Object[]{};
}

    public ResultadoServices() {
        this.resultadoDao = new ResultadoDao();
    }

    public Resultado getResultado() {
        if (resultado == null) {
            resultado = new Resultado();
        }
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public LinkedList<Resultado> listAll() {
        return resultadoDao.getListAll();
    }

    public Resultado get(Integer id) throws Exception {
        return resultadoDao.get(id);
    }

    public Boolean save() throws Exception {
        return resultadoDao.save();
    }

    public void saveEncuentro(Encuentro encuentro) {

    }


    public Boolean update() throws Exception {
        return resultadoDao.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return resultadoDao.delete(id);
    }

    public LinkedList<Resultado> ordenarPorEquipoGanador(boolean ascendente) throws Exception {
        return resultadoDao.ordenarPorEquipoGanador(ascendente);
    }

    public LinkedList<Resultado> ordenarPorEquipoPerdedor(boolean ascendente) throws Exception {
        return resultadoDao.ordenarPorEquipoPerdedor(ascendente);
    }

    public LinkedList<Resultado> ordenarPorGolesEquipo1(boolean ascendente) throws Exception {
        return resultadoDao.ordenarPorGolesEquipo1(ascendente);
    }

    public LinkedList<Resultado> ordenarPorGolesEquipo2(boolean ascendente) throws Exception {
        return resultadoDao.ordenarPorGolesEquipo2(ascendente);
    }

    public LinkedList<Resultado> ordenarPorPuntosEncuentro(boolean ascendente) throws Exception {
        return resultadoDao.ordenarPorPuntosEncuentro(ascendente);
    }

    public LinkedList<Resultado> buscarPorEquipoGanador(String equipoGanador) {
        return resultadoDao.buscarPorEquipoGanador(equipoGanador);
    }

    public LinkedList<Resultado> buscarPorEquipoPerdedor(String equipoPerdedor) {
        return resultadoDao.buscarPorEquipoPerdedor(equipoPerdedor);
    }

    public LinkedList<Resultado> buscarPorGolesEquipo1(Integer golesEquipo1) {
        return resultadoDao.buscarPorGolesEquipo1(golesEquipo1);
    }

    public LinkedList<Resultado> buscarPorGolesEquipo2(Integer golesEquipo2) {  
        return resultadoDao.buscarPorGolesEquipo2(golesEquipo2);  
    }

    public LinkedList<Resultado> buscarPorPuntosEncuentro(Integer puntosEncuentro) {
        return resultadoDao.buscarPorPuntosEncuentro(puntosEncuentro);
    }

}
