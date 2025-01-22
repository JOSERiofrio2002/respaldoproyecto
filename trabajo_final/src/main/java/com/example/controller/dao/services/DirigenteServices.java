package com.example.controller.dao.services;

import com.example.controller.dao.DirigenteDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Dirigente;
import com.example.models.enumerador.Genero;
import com.example.models.enumerador.TipoIdentificacion;

public class DirigenteServices {
    private DirigenteDao obj;

    public DirigenteServices() {
        obj = new DirigenteDao();
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
        return obj.getlistAll();
    }

    public Dirigente getDirigente() {
        return obj.getDirigente();
    }

    public void setDirigente(Dirigente dirigente) {
        obj.setDirigente(dirigente);
    }

    public Dirigente get(Integer id) throws Exception {
        return obj.get(id);
    }

    public TipoIdentificacion getTipoIdentificacion(String tipo) {
        return obj.getTipoIdentificacion(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }

    public Genero getTipoGenero(String genero) {
        return obj.getTipoGenero(genero);
    }

    public Genero[] getGenero() {
        return obj.getGenero();
    }
}
