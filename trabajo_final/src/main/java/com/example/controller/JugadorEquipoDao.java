package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.JugadorEquipo;

public class JugadorEquipoDao extends AdapterDao<JugadorEquipo>{
    private JugadorEquipo jugadorEquipo;
    private LinkedList listAll;

    public JugadorEquipoDao(){
        super(JugadorEquipo.class);
    }

    public JugadorEquipo getJugadorEquipo() {
        if (jugadorEquipo == null) {
            jugadorEquipo = new JugadorEquipo();
        }
        return jugadorEquipo;
    }

    public void setJugadorEquipo(JugadorEquipo jugadorEquipo) {
        this.jugadorEquipo = jugadorEquipo;
    }

    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        jugadorEquipo.setId(id);
        this.persist(this.jugadorEquipo);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getJugadorEquipo(), getJugadorEquipo().getId()-1);
        this.listAll = listAll();
        return true;
    }
}
