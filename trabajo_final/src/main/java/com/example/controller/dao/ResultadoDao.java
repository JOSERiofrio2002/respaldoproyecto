package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Resultado;

public class ResultadoDao extends AdapterDao<Resultado> {
    private Resultado resultado;
    private LinkedList<Resultado> listAll;

    public ResultadoDao() {
        super(Resultado.class);
    }

    public Resultado getResultado() {
        if (resultado == null) {
            resultado = new Resultado();
        }
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public LinkedList<Resultado> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        resultado.setId(id);
        this.persist(this.resultado);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getResultado(), getResultado().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(Integer id) throws Exception {
        LinkedList<Resultado> list = getListAll();
        Resultado resultado = get(id);
        if (resultado != null) {
            list.remove(resultado);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("Resultado con id " + id + " no encontrado.");
            return false;
        }
    }

    public LinkedList<Resultado> ordenarPorEquipoGanador(boolean ascendente) throws Exception {
        LinkedList<Resultado> resultados = getListAll();
        resultados.quicksort("equipoGanador", ascendente ? 1 : -1);
        return resultados;
    }

    public LinkedList<Resultado> ordenarPorEquipoPerdedor(boolean ascendente) throws Exception {
        LinkedList<Resultado> resultados = getListAll();
        resultados.quicksort("equipoPerdedor", ascendente ? 1 : -1);
        return resultados;
    }

    public LinkedList<Resultado> ordenarPorGolesEquipo1(boolean ascendente) throws Exception {
        LinkedList<Resultado> resultados = getListAll();
        resultados.quicksort("golesEquipo1", ascendente ? 1 : -1);
        return resultados;
    }

    public LinkedList<Resultado> ordenarPorGolesEquipo2(boolean ascendente) throws Exception {
        LinkedList<Resultado> resultados = getListAll();
        resultados.quicksort("golesEquipo2", ascendente ? 1 : -1);
        return resultados;
    }

    public LinkedList<Resultado> ordenarPorPuntosEncuentro(boolean ascendente) throws Exception {
        LinkedList<Resultado> resultados = getListAll();
        resultados.quicksort("puntosEncuentro", ascendente ? 1 : -1);
        return resultados;
    }

    public LinkedList<Resultado> buscarPorEquipoGanador(String equipoGanador) {
        LinkedList<Resultado> resultados = new LinkedList<>();
        for (Resultado resultado : getListAll().toArray()) {
            if (resultado.getEquipoGanador().equalsIgnoreCase(equipoGanador)) {
                resultados.add(resultado);
            }
        }
        return resultados;
    }

    public LinkedList<Resultado> buscarPorEquipoPerdedor(String equipoPerdedor) {
        LinkedList<Resultado> resultados = new LinkedList<>();
        for (Resultado resultado : getListAll().toArray()) {
            if (resultado.getEquipoPerdedor().equalsIgnoreCase(equipoPerdedor)) {
                resultados.add(resultado);
            }
        }
        return resultados;
    }

    public LinkedList<Resultado> buscarPorGolesEquipo1(Integer golesEquipo1) {
        LinkedList<Resultado> resultados = new LinkedList<>();
        for (Resultado resultado : getListAll().toArray()) {
            if (resultado.getGolesEquipo1().equals(golesEquipo1)) {
                resultados.add(resultado);
            }
        }
        return resultados;
    }

    public LinkedList<Resultado> buscarPorGolesEquipo2(Integer golesEquipo2) {
        LinkedList<Resultado> resultados = new LinkedList<>();
        for (Resultado resultado : getListAll().toArray()) {
            if (resultado.getGolesEquipo2().equals(golesEquipo2)) {
                resultados.add(resultado);
            }
        }
        return resultados;
    }

    public LinkedList<Resultado> buscarPorPuntosEncuentro(Integer puntosEncuentro) {
        LinkedList<Resultado> resultados = new LinkedList<>();
        for (Resultado resultado : getListAll().toArray()) {
            if (resultado.getPuntosEncuentro().equals(puntosEncuentro)) {
                resultados.add(resultado);
            }
        }
        return resultados;
    }

}