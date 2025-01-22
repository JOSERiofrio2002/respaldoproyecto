package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Calendario;

public class CalendarioDao extends AdapterDao<Calendario>{
    private Calendario calendario;
    private LinkedList listAll;

    public CalendarioDao() {
        super(Calendario.class);
    }

    public Calendario getCalendario() {
        if (calendario == null){
            calendario = new Calendario();
        }
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        calendario.setId(id);
        this.persist(this.calendario);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getCalendario(), getCalendario().getId()-1);
        this.listAll = listAll();
        return true;
    }
}
