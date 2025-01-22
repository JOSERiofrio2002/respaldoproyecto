package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Infraccion;

public class InfraccionDao extends AdapterDao<Infraccion> {
    private Infraccion infraccion;
    private LinkedList<Infraccion> listAll;

    public InfraccionDao() {
        super(Infraccion.class);
    }

    public Infraccion getInfraccion() {
        if (infraccion == null) {
            infraccion = new Infraccion();
        }
        return infraccion;
    }

    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }

    public LinkedList<Infraccion> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        infraccion.setId(id);
        this.persist(this.infraccion);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getInfraccion(), getInfraccion().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Infraccion> list = getListAll();
        Infraccion infraccion = get(id);
        if (infraccion != null) {
            list.remove(infraccion);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Infraccion con id " + id + " no encontrada.");
            return false;
        }
    }

    public LinkedList<Infraccion> ordenarPorNumTarjeta(boolean ascendente) throws Exception {
        LinkedList<Infraccion> infracciones = getListAll();
        infracciones.quicksort("numTarjeta", ascendente ? 1 : -1);
        return infracciones;
    }

    public LinkedList<Infraccion> ordenarPorIdentificacionJugador(boolean ascendente) throws Exception {
        LinkedList<Infraccion> infracciones = getListAll();
        infracciones.quicksort("identificacionJugador", ascendente ? 1 : -1);
        return infracciones;
    }

    public LinkedList<Infraccion> ordenarPorColorTarjeta(boolean ascendente) throws Exception {
        LinkedList<Infraccion> infracciones = getListAll();
        infracciones.quicksort("colorTarjeta", ascendente ? 1 : -1);
        return infracciones;
    }

    public LinkedList<Infraccion> buscarPorNumTarjeta(Integer numTarjeta) {
        LinkedList<Infraccion> resultados = new LinkedList<>();
        for (Infraccion infraccion : getListAll().toArray()) {
            if (infraccion.getNumTarjeta().equals(numTarjeta)) {
                resultados.add(infraccion);
            }
        }
        return resultados;
    }

    public LinkedList<Infraccion> buscarPorIdentificacionJugador(String identificacionJugador) {
        LinkedList<Infraccion> resultados = new LinkedList<>();
        for (Infraccion infraccion : getListAll().toArray()) {
            if (infraccion.getIdentificacionJugador().equalsIgnoreCase(identificacionJugador)) {
                resultados.add(infraccion);
            }
        }
        return resultados;
    }

    public LinkedList<Infraccion> buscarPorColorTarjeta(String colorTarjeta) {
        LinkedList<Infraccion> resultados = new LinkedList<>();
        for (Infraccion infraccion : getListAll().toArray()) {
            if (infraccion.getColorTarjeta().toString().equalsIgnoreCase(colorTarjeta)) {
                resultados.add(infraccion);
            }
        }
        return resultados;
    }


}