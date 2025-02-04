package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Inscripcion;

public class InscripcionDao extends AdapterDao<Inscripcion> {

    private Inscripcion inscripcion;
    private LinkedList<Inscripcion> listAll;  

    public InscripcionDao() {
        super(Inscripcion.class);
    }

    public Inscripcion getInscripcion() {
        if (inscripcion == null) {
            inscripcion = new Inscripcion();
        }
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public LinkedList<Inscripcion> getlistAll() {
        if (listAll == null) { // Inicializa listAll si es null
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        LinkedList<Inscripcion> currentList = getlistAll(); // Obtén la lista actual
        Integer id = currentList.getSize() + 1; // Calcula el ID
        inscripcion.setId(id); // Establece el ID de la inscripción
        this.persist(this.inscripcion); // Persiste la inscripción
        this.listAll = currentList; // Actualiza la lista
        return true;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getInscripcion(), getInscripcion().getId() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Inscripcion> list = getlistAll();
        Inscripcion inscripcion = get(id);
        if (inscripcion != null) {
            list.remove(inscripcion);
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
