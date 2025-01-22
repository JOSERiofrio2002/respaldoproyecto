package com.example.controller.dao.services;

import com.example.controller.dao.ColorTarjetNumDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.enumerador.ColorTarjeta;


public class ColorTarjetaServices {

    private ColorTarjetNumDao obj;

    public ColorTarjetaServices() {
        obj = new ColorTarjetNumDao();
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

    public ColorTarjeta getColorTarjeta() {
        return obj.getColorTarjeta();
    }


    public ColorTarjeta get(Integer id) throws Exception {
        return obj.get(id);
    }

    public ColorTarjeta getColorTarjeta(String name) {
        return obj.getColorTarjeta(name);
    }

    public ColorTarjeta[] getTipos() {
        return obj.getTipos();
    }


    
}
