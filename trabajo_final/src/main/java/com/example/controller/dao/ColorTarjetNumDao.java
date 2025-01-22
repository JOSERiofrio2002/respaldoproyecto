package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.enumerador.ColorTarjeta;


public class ColorTarjetNumDao extends AdapterDao<ColorTarjeta>{
   
    private ColorTarjeta colorTarjeta;
    private LinkedList<ColorTarjeta> listAll;
    // private LinkedList<Persona> listAll;

    public ColorTarjetNumDao() {
    super(ColorTarjeta.class);
        this.listAll = new LinkedList<>();
    }

    public ColorTarjeta getColorTarjeta() {
        if (colorTarjeta == null) {
           colorTarjeta = ColorTarjeta.AMARILLO; 
        }
        return colorTarjeta;
    }


    public LinkedList<ColorTarjeta> getlistAll() {
        if (listAll.isEmpty()) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public ColorTarjeta getColorTarjeta(String name) {
        return ColorTarjeta.valueOf(name);
    }
    
    public ColorTarjeta[] getTipos() {
        return ColorTarjeta.values();
    }

 

    public Boolean update() throws Exception {
        try {
            this.merge(getColorTarjeta(), getColorTarjeta().ordinal());
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<ColorTarjeta> list = getlistAll();
        ColorTarjeta dirigente = get(id);
        if (dirigente != null) {
            list.remove(dirigente);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Persona con id " + id + " no encontrada.");
            return false;
        }
    }


}
