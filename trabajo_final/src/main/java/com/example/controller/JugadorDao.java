package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Jugador;

public class JugadorDao extends AdapterDao<Jugador>{
    private Jugador jugador;
    private LinkedList listAll;

    public JugadorDao() {
        super(Jugador.class);
    }

    public Jugador getJugador() {
        if (jugador == null){
            jugador = new Jugador();
        }
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        jugador.setId(id);
        this.persist(this.jugador);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getJugador(), getJugador().getId()-1);
        this.listAll = listAll();
        return true;
    }

}
