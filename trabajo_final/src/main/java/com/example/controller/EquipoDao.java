package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Equipo;

public class EquipoDao extends AdapterDao<Equipo>{
    private Equipo equipo;
    private LinkedList listAll;

    public EquipoDao() {
        super(Equipo.class);
    }

    public Equipo getEquipo() {
        if (equipo == null) {
            equipo = new Equipo();
        }
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        equipo.setId(id);
        this.persist(this.equipo);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getEquipo(), getEquipo().getId()-1);
        this.listAll = listAll();
        return true;
    }
}
