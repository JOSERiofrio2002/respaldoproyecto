package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Calendario;

public class CalendarioDao extends AdapterDao<Calendario> {

    private Calendario calendario;
    private LinkedList<Calendario> listAll;  

    public CalendarioDao() {
        super(Calendario.class);
    }

    public Calendario getCalendario() {
        if (calendario == null) {
            calendario = new Calendario();
        }
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public LinkedList<Calendario> getlistAll() {
        if (listAll == null) { // Inicializa listAll si es null
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        LinkedList<Calendario> currentList = getlistAll(); // Obtén la lista actual
        Integer id = currentList.getSize() + 1; // Calcula el ID
        calendario.setId(id); // Establece el ID de la inscripción
        this.persist(this.calendario); // Persiste la inscripción
        this.listAll = currentList; // Actualiza la lista
        return true;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getCalendario(), getCalendario().getId() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Calendario> list = getlistAll();
        Calendario calendario = get(id);
        if (calendario != null) {
            list.remove(calendario);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Inscripción con id " + id + " no encontrada.");
            return false;
        }
    }
}
