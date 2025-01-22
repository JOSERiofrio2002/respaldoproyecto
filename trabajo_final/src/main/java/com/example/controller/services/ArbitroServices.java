package com.example.controller.services;

import com.example.controller.dao.ArbitroDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Arbitro;

public class ArbitroServices {
    private ArbitroDao arbitroDao;
    private Arbitro arbitro;

    public ArbitroServices() {
        this.arbitroDao = new ArbitroDao();
    }

    public Arbitro getArbitro() {
        if (arbitro == null) {
            arbitro = new Arbitro();
        }
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public LinkedList<Arbitro> listAll() {
        return arbitroDao.getListAll();
    }

    public String getTipoIdentificacion(String tipo) {
        return arbitroDao.getTipoIdentificacion();
    }

    public String getTipoGenero(String genero) {
        return arbitroDao.getGenero();
    }

    public Arbitro get(Integer id) throws Exception {
        return arbitroDao.get(id);
    }

    public Boolean save() throws Exception {
        return arbitroDao.save();
    }

    public Boolean update() throws Exception {
        return arbitroDao.update();
    }

    public Boolean delete(Integer id) throws Exception {
        return arbitroDao.delete(id);
    }

    public LinkedList<Arbitro> ordenarPorNombre(boolean ascendente) throws Exception {
        return arbitroDao.ordenarPorNombre(ascendente);
    }

    public LinkedList<Arbitro> ordenarPorApellido(boolean ascendente) throws Exception {
        return arbitroDao.ordenarPorApellido(ascendente);
    }

    public LinkedList<Arbitro> ordenarPorIdentificacion(boolean ascendente) throws Exception {
        return arbitroDao.ordenarPorIdentificacion(ascendente);
    }

    public LinkedList<Arbitro> ordenarPorFechaNacimiento(boolean ascendente) throws Exception {
        return arbitroDao.ordenarPorFechaNacimiento(ascendente);
    }

    public LinkedList<Arbitro> ordenarPorAsociacion(boolean ascendente) throws Exception {
        return arbitroDao.ordenarPorAsociacion(ascendente);
    }

    public LinkedList<Arbitro> buscarPorNombre(String nombre) {
        return arbitroDao.buscarPorNombre(nombre);
    }

    public LinkedList<Arbitro> buscarPorApellido(String apellido) {
        return arbitroDao.buscarPorApellido(apellido);
    }

    public LinkedList<Arbitro> buscarPorIdentificacion(String identificacion) {
        return arbitroDao.buscarPorIdentificacion(identificacion);
    }

    public LinkedList<Arbitro> buscarPorAsociacion(String asociacion) {
        return arbitroDao.buscarPorAsociacion(asociacion);
    }

    public LinkedList<Arbitro> buscarPorGenero(String genero) {
        return arbitroDao.buscarPorGenero(genero);
    }

}