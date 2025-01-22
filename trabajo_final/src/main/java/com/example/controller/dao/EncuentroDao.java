package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Encuentro;

public class EncuentroDao extends AdapterDao<Encuentro> {
    private Encuentro encuentro;
    private LinkedList<Encuentro> listAll;

    public EncuentroDao() {
        super(Encuentro.class);
    }

    public Encuentro getEncuentro() {
        if (encuentro == null) {
            encuentro = new Encuentro();
        }
        return encuentro;
    }

    public void setEncuentro(Encuentro encuentro) {
        this.encuentro = encuentro;
    }

    public LinkedList<Encuentro> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        encuentro.setId(id);
        this.persist(this.encuentro);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getEncuentro(), getEncuentro().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Encuentro> list = getListAll();
        Encuentro encuentro = get(id);
        if (encuentro != null) {
            list.remove(encuentro);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Encuentro con id " + id + " no encontrado.");
            return false;
        }
    }

    public LinkedList<Encuentro> ordenarPorIdInscrito1(boolean ascendente) throws Exception {
        LinkedList<Encuentro> encuentros = getListAll();
        encuentros.quicksort("idInscrito1", ascendente ? 1 : -1);
        return encuentros;
    }

    public LinkedList<Encuentro> ordenarPorIdInscrito2(boolean ascendente) throws Exception {
        LinkedList<Encuentro> encuentros = getListAll();
        encuentros.quicksort("idInscrito2", ascendente ? 1 : -1);
        return encuentros;
    }

    public LinkedList<Encuentro> ordenarPorUbicacion(boolean ascendente) throws Exception {
        LinkedList<Encuentro> encuentros = getListAll();
        encuentros.quicksort("ubicacion", ascendente ? 1 : -1);
        return encuentros;
    }

    public LinkedList<Encuentro> ordenarPorIdentificacion(boolean ascendente) throws Exception {
        LinkedList<Encuentro> encuentros = getListAll();
        encuentros.quicksort("identificacion", ascendente ? 1 : -1);
        return encuentros;
    }

    public LinkedList<Encuentro> ordenarPorHoraInicio(boolean ascendente) throws Exception {
        LinkedList<Encuentro> encuentros = getListAll();
        encuentros.quicksort("horaInicio", ascendente ? 1 : -1);
        return encuentros;
    }

    public LinkedList<Encuentro> buscarPorIdInscrito1(Integer idInscrito1) {
        LinkedList<Encuentro> resultados = new LinkedList<>();
        for (Encuentro encuentro : getListAll().toArray()) {
            if (encuentro.getIdInscrito1().equals(idInscrito1)) {
                resultados.add(encuentro);
            }
        }
        return resultados;
    }

    public LinkedList<Encuentro> buscarPorIdInscrito2(Integer idInscrito2) {
        LinkedList<Encuentro> resultados = new LinkedList<>();
        for (Encuentro encuentro : getListAll().toArray()) {
            if (encuentro.getIdInscrito2().equals(idInscrito2)) {
                resultados.add(encuentro);
            }
        }
        return resultados;
    }

    public LinkedList<Encuentro> buscarPorUbicacion(String ubicacion) {
        LinkedList<Encuentro> resultados = new LinkedList<>();
        for (Encuentro encuentro : getListAll().toArray()) {
            if (encuentro.getUbicacion().equalsIgnoreCase(ubicacion)) {
                resultados.add(encuentro);
            }
        }
        return resultados;
    }

    public LinkedList<Encuentro> buscarPorIdentificacion(String identificacion) {
        LinkedList<Encuentro> resultados = new LinkedList<>();
        for (Encuentro encuentro : getListAll().toArray()) {
            if (encuentro.getIdentificacion().equalsIgnoreCase(identificacion)) {
                resultados.add(encuentro);
            }
        }
        return resultados;
    }

    public LinkedList<Encuentro> buscarPorHoraInicio(String horaInicio) {
        LinkedList<Encuentro> resultados = new LinkedList<>();
        for (Encuentro encuentro : getListAll().toArray()) {
            if (encuentro.getHoraInicio().toString().equalsIgnoreCase(horaInicio)) {
                resultados.add(encuentro);
            }
        }
        return resultados;
    }



}