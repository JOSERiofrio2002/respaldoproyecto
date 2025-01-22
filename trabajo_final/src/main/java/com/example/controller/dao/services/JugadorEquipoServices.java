package com.example.controller.dao.services;

import com.example.controller.dao.JugadorEquipoDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.JugadorEquipo;


public class JugadorEquipoServices {
    private JugadorEquipoDao obj;

    public JugadorEquipoServices() {
        obj = new JugadorEquipoDao();
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

    public JugadorEquipo getJugadorEquipo() {
        return obj.getJugadorEquipo();
    }

    public void setJugadorEquipo(JugadorEquipo jugadorEquipo) {
        obj.setJugadorEquipo(jugadorEquipo);
    }

    public JugadorEquipo get(Integer id) throws Exception {
        return obj.get(id);
    }
}
