package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Persona;

public class PersonaDao extends AdapterDao<Persona> {

    private Persona persona;
    private LinkedList<Persona> listAll;

    public PersonaDao() {
        super(Persona.class);
    }

    // Obtener objeto Persona (si está vacío, crea el objeto)
    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkedList<Persona> getListAll() {
        if (listAll == null) {
            listAll = listAll();
        }
        return listAll;
    }

    // Método para guardar una nueva Persona
    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        persona.setId(id);
        this.persist(persona);
        this.listAll = listAll();
        return true;
    }

    // Método para actualizar una Persona existente
    public Boolean update() throws Exception {
        this.merge(getPersona(), getPersona().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    // Método de borrado lógico para inhabilitar una Persona
    public Boolean delete(int id) throws Exception {
        Persona persona = get(id); // Obtiene la Persona por ID
        if (persona != null) {
            persona.setActivo(false); // Borrado lógico (inactiva la persona)
            this.update(); // Actualiza en la lista
            this.listAll = listAll(); // Refresca la lista
            return true;
        }
        return false; // Retorna false si no encuentra el id
    }

    // Método para habilitar o deshabilitar una Persona
    public Boolean habilitar_deshabilitar(int id) throws Exception {
        Persona persona = get(id); // Obtiene la Persona por ID
        if (persona != null) {
            persona.setActivo(!persona.getisActivo()); // Alterna el estado activo
            this.update(); // Actualiza en la lista
            this.listAll = listAll(); // Refresca la lista
            return true;
        }
        return false;
    }

    // Método para obtener una Persona específica por su ID
    public Persona get(int id) throws Exception {
        for (int i = 0; i < getListAll().getSize(); i++) {
            Persona currentPersona = getListAll().get(i);
            if (currentPersona.getId() == id) {
                return currentPersona;
            }
        }
        return null;
    }
}
