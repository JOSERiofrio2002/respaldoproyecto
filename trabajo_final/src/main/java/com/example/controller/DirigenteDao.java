package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Dirigente;

public class DirigenteDao extends AdapterDao<Dirigente> {
    private Dirigente dirigente;
    private LinkedList listAll;
    
    public DirigenteDao(){
        super(Dirigente.class);
    }


    public Dirigente getDirigente() {
        if (dirigente == null) {
            dirigente = new Dirigente();
        }
        return dirigente;
    }

    public void setDirigente(Dirigente dirigente) {
        this.dirigente = dirigente;
    }

    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        dirigente.setId(id);
        this.persist(this.dirigente);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getDirigente(), getDirigente().getId()-1);
        this.listAll = listAll();
        return true;
    }
    
}
