package com.example.controller;

import java.util.List;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Campeonato;
import com.example.models.Reglamento;

public class CampeonatoDao extends AdapterDao<Campeonato> {

    private Campeonato campeonato;
    private LinkedList<Campeonato> listAll;  

    public CampeonatoDao() {
        super(Campeonato.class);
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

    public LinkedList<Campeonato> getlistAll() {
        if (listAll == null) { // Inicializa listAll si es null
            this.listAll = listAll();
        }
        return listAll;
    }

    // Método para obtener los reglamentos de un campeonato
  

    // Método para agregar un reglamento a un campeonato
    public Boolean addReglamentoToCampeonato(Integer campeonatoId, Reglamento reglamento) throws Exception {
        Campeonato campeonato = get(campeonatoId);
        if (campeonato != null) {
            campeonato.addReglamento(reglamento); // Agrega el reglamento a la lista de reglamentos del campeonato
            this.merge(campeonato, campeonatoId - 1); // Guarda el campeonato actualizado
            return true;
        }
        return false;
    }

    // Método para guardar un campeonato con sus reglamentos
    public Boolean save() throws Exception {
        LinkedList<Campeonato> currentList = getlistAll(); // Obtén la lista actual
        Integer id = currentList.getSize() + 1; // Calcula el ID
        campeonato.setId(id); // Establece el ID de la inscripción
        this.persist(this.campeonato); // Persiste el campeonato
        this.listAll = currentList; // Actualiza la lista
        return true;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getCampeonato(), getCampeonato().getId() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Campeonato> list = getlistAll();
        Campeonato campeonato = get(id);
        if (campeonato != null) {
            list.remove(campeonato);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Campeonato con id " + id + " no encontrado.");
            return false;
        }
    }
    // Método para obtener los reglamentos de un campeonato
public LinkedList<Reglamento> getReglamentosDeCampeonato(Integer campeonatoId) throws Exception {
    Campeonato campeonato = get(campeonatoId);  // Obtener el campeonato por su ID
    if (campeonato != null) {
        // Supongamos que campeonato.getReglamentos() devuelve un List<Reglamento>
        List<Reglamento> listaDeReglamentos = campeonato.getReglamentos(); 

        // Convertir la lista de reglamentos (List<Reglamento>) a LinkedList<Reglamento>
        LinkedList<Reglamento> reglamentos = new LinkedList<>();
        for (Reglamento reglamento : listaDeReglamentos) {
            reglamentos.add(reglamento); // Agregar cada reglamento al LinkedList
        }
        
        return reglamentos;  // Devolver la lista convertida
    }
    
    // Si no encuentra el campeonato, devuelve una lista vacía
    return new LinkedList<>(); 
}

}