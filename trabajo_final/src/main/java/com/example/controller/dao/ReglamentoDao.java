package com.example.controller.dao;

import com.example.controller.tda.list.LinkedList;

import com.example.controller.dao.implement.AdapterDao;
import com.example.models.Reglamento;

public class ReglamentoDao extends AdapterDao<Reglamento>{
    private Reglamento reglamento;
    private LinkedList listAll;

    public ReglamentoDao() {
        super(Reglamento.class);
    }

    public Reglamento getReglamento() {
        if (reglamento == null){
            reglamento = new Reglamento();
        }
        return reglamento;
    }

    public void setReglamento(Reglamento reglamento) {
        this.reglamento = reglamento;
    }
    
    public LinkedList getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        reglamento.setId(id);
        this.persist(this.reglamento);
        this.listAll = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getReglamento(), getReglamento().getId()-1);
        this.listAll = listAll();
        return true;
    }
}
