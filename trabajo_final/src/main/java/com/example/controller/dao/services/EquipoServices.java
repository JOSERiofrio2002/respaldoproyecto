package com.example.controller.dao.services;

import com.example.controller.dao.EquipoDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Equipo;

public class EquipoServices {
    private EquipoDao obj;

    public EquipoServices() {
        obj = new EquipoDao();
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

    public Equipo getEquipo() {
        return obj.getEquipo();
    }

    public void setEquipo(Equipo equipo) {
        obj.setEquipo(equipo);
    }

    public Equipo get(Integer id) throws Exception {
        return obj.get(id);
    }
    
}
