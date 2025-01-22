package com.example.controller.dao.services;

import com.example.controller.dao.JugadorDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Jugador;
import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

public class JugadorServices {
    private JugadorDao obj;

    public JugadorServices() {
        obj = new JugadorDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }
    
    public Boolean update() throws Exception{
        return obj.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id);
    }
    
    public LinkedList listAll(){
        return obj.getlistAll();
    }

    public Jugador getJugador() {
        return obj.getJugador();
    }

    public void setJugador(Jugador jugador) {
        obj.setJugador(jugador);
    }

    public Jugador get(Integer id) throws Exception {
        return obj.get(id);
    }

        public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return obj.getTipoIdentificacion(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }

    public Genero getTipoGenero(String genero) {
        return obj.getTipoGenero(genero);
    }

    public Genero[] getGenero() {
        return obj.getGenero();
    }
}
