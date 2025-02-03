package com.example.controller.dao.services;

import com.example.controller.dao.CampeonatoDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Campeonato;
import com.example.models.enumerador.TipoCategoria;

public class CampeonatoServices {
    private CampeonatoDao campeonatoDao;
    private Campeonato campeonato;

    public CampeonatoServices() {
        this.campeonatoDao = new CampeonatoDao();
    }

    public Campeonato getCampeonato() {
        if (campeonato == null) {
            campeonato = new Campeonato();
        }
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public LinkedList<Campeonato> listAll() {
        return campeonatoDao.getListAll();
    }

    public Campeonato get(Integer id) throws Exception {
        return campeonatoDao.get(id);
    }

    public Boolean save() throws Exception {
        return campeonatoDao.save();
    }

    public Boolean update() throws Exception {
        return campeonatoDao.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return campeonatoDao.delete(id);
    }

     public TipoCategoria getCategoria() {
        return campeonato.getCategoria();

    }

    public void setCategoria(TipoCategoria categoria) {
        campeonato.setCategoria(categoria);
    }



    


}