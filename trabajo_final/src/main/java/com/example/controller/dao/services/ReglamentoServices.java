package com.example.controller.dao.services;

import com.example.controller.dao.ReglamentoDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Reglamento;

public class ReglamentoServices {
    private ReglamentoDao obj;

    public ReglamentoServices() {
        obj = new ReglamentoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id);
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Reglamento get(Integer id) throws Exception {
        return obj.get(id);
    }

    public Reglamento getReglamento() {
        return obj.getReglamento();
    }

    public void setReglamento(Reglamento reglamento){
        obj.setReglamento(reglamento);
    }

    public void getFormato() {
        obj.getFormato();
    }

    public void setFormato() {
        obj.getFormato();
    }




}
