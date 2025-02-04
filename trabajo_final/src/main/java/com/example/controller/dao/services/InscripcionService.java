package com.example.controller.dao.services;

import com.example.controller.InscripcionDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Inscripcion;

public class InscripcionService {
    private InscripcionDao obj;

    public InscripcionService() {
        obj = new InscripcionDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return obj.delete(id); // Llamar al método delete de PersonaDao
    }

    public LinkedList<Inscripcion> listAll() {
        return obj.getlistAll();
    }

    public Inscripcion getInscripcion() {
        return obj.getInscripcion();
    }

    public void setInscripcion(Inscripcion inscripcion) {
        obj.setInscripcion(inscripcion);
    }

    public Inscripcion get(Integer id) throws Exception {
        return obj.get(id);
    }
}
