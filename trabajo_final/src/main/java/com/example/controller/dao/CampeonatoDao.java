package com.example.controller.dao;

import  com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Campeonato;

public class CampeonatoDao extends AdapterDao<Campeonato>{
    private Campeonato campeonato;
    private LinkedList listAll;

    public CampeonatoDao() {
        super(Campeonato.class);
    }

    public Campeonato getCampeonato() {
        if (campeonato == null){
            campeonato = new Campeonato();
        }
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        campeonato.setId(id);
        this.persist(this.campeonato);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getCampeonato(), getCampeonato().getId()-1);
        this.listAll = listAll();
        return true;
    }
}
