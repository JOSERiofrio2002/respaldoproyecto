package com.example.controller;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Reglamento;

public class ReglamentoDao extends AdapterDao<Reglamento> {

    private Reglamento reglamento;
    private LinkedList<Reglamento> listAll;  

    public ReglamentoDao() {
        super(Reglamento.class);
    }

    public Reglamento getReglamento() {
        if (reglamento == null) {
            reglamento = new Reglamento();
        }
        return reglamento;
    }

    public void setReglamento(Reglamento reglamento) {
        this.reglamento = reglamento;
    }

    public LinkedList<Reglamento> getlistAll() {
        if (listAll == null) { // Inicializa listAll si es null
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        LinkedList<Reglamento> currentList = getlistAll(); // Obtén la lista actual
        Integer id = currentList.getSize() + 1; // Calcula el ID
        reglamento.setId(id); // Establece el ID de la inscripción
        this.persist(this.reglamento); // Persiste la inscripción
        this.listAll = currentList; // Actualiza la lista
        return true;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getReglamento(), getReglamento().getId() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Reglamento> list = getlistAll();
        Reglamento reglamento = get(id);
        if (reglamento != null) {
            list.remove(reglamento);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Inscripción con id " + id + " no encontrada.");
            return false;
        }
    }

    public Boolean associateWithCampeonato(Integer reglamentoId, Integer campeonatoId) throws Exception {
        Reglamento reglamento = get(reglamentoId);
        if (reglamento != null) {
            // Establecer el campeonato relacionado en el reglamento
            reglamento.setIdCampeonato(campeonatoId);
            this.merge(reglamento, reglamentoId);
            return true;
        }
        return false;
    }

}

