package com.example.controller.dao.services;

import java.util.HashMap;

import com.example.controller.dao.EncuentroDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Encuentro;
import com.example.models.Resultado;

public class EncuentroServices {

    private EncuentroDao encuentroDao;
    private Encuentro encuentro;
    public EncuentroServices() {
        this.encuentroDao = new EncuentroDao();
    }

    public Encuentro getEncuentro() {
        if (encuentro == null) {
            encuentro = new Encuentro();
        }
        return encuentro;
    }

    public void setEncuentro(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    public void getEncuentro(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    public LinkedList<Encuentro> listAll() {
        return encuentroDao.getListAll();
    }

    public Object[] getListAll() throws Exception {
        if(!encuentroDao.getListAll().isEmpty()) {
            Encuentro[] lista = (Encuentro[]) encuentroDao.getListAll().toArray();
            Object[] respuesta = new Object[lista.length];
            for(int i = 0; i < lista.length; i++) {
                Resultado p = new ResultadoServices().get(lista[i].getId());

                HashMap mapa = new HashMap<>();
                mapa.put("id", lista[i].getId());
                mapa.put("idInscrito1", lista[i].getIdInscrito1());
                mapa.put("idInscrito2", lista[i].getIdInscrito2());
                mapa.put("ubicacion", lista[i].getUbicacion());
                mapa.put("identificacion", lista[i].getIdentificacion());
                mapa.put("estado", lista[i].getEstado());
                mapa.put("horaInicio", lista[i].getHoraInicio());
                mapa.put("resultado", p);

                respuesta[i] = mapa;
            }
            return respuesta;
        }
        return new Object[]{};
    }

    public Encuentro get(Integer id) throws Exception {
        return encuentroDao.get(id);
    }

    public Boolean save() throws Exception {
        return encuentroDao.save();
    }

    public Boolean update() throws Exception {
        return encuentroDao.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return encuentroDao.delete(id);
    }

    public LinkedList<Encuentro> ordenarPorIdInscrito1(boolean ascendente) throws Exception {
        return encuentroDao.ordenarPorIdInscrito1(ascendente);
    }
    
    public LinkedList<Encuentro> ordenarPorIdInscrito2(boolean ascendente) throws Exception {
        return encuentroDao.ordenarPorIdInscrito2(ascendente);
    }

    public LinkedList<Encuentro> ordenarPorUbicacion(boolean ascendente) throws Exception {
        return encuentroDao.ordenarPorUbicacion(ascendente);
    }

    public LinkedList<Encuentro> ordenarPorIdentificacion(boolean ascendente) throws Exception {
        return encuentroDao.ordenarPorIdentificacion(ascendente);
    }

    public LinkedList<Encuentro> ordenarPorHoraInicio(boolean ascendente) throws Exception {
        return encuentroDao.ordenarPorHoraInicio(ascendente);
    }

    public LinkedList<Encuentro> buscarPorIdInscrito1(Integer idInscrito1) {
        return encuentroDao.buscarPorIdInscrito1(idInscrito1);
    }
    
    public LinkedList<Encuentro> buscarPorIdInscrito2(Integer idInscrito2) {
        return encuentroDao.buscarPorIdInscrito2(idInscrito2);
    }

    public LinkedList<Encuentro> buscarPorUbicacion(String ubicacion) {
        return encuentroDao.buscarPorUbicacion(ubicacion);
    }

    public LinkedList<Encuentro> buscarPorIdentificacion(String identificacion) {
        return encuentroDao.buscarPorIdentificacion(identificacion);
    }

    public LinkedList<Encuentro> buscarPorHoraInicio(String horaInicio) {
        return encuentroDao.buscarPorHoraInicio(horaInicio);
    }

   
}
