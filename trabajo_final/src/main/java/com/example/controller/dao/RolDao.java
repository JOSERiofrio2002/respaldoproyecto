package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Rol;

public class RolDao extends AdapterDao<Rol>{

    // Atributos
    private Rol role;
    private LinkedList listRols;

    public RolDao(){
        super(Rol.class);
    }

    public Rol getRol(){
        if(role == null){
            role = new Rol();
        }
        return role;
    }

    public void setRol(Rol r){
        this.role = r;
    }

    public LinkedList getListAll() {
        if(listRols == null){
            this.listRols = listAll();
        }
        return listRols;
    }

    // CRUD
    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        role.setId(id);
        this.persist(this.role);
        this.listRols = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getRol(), getRol().getId()-1);
        this.listRols = listAll();
        return true;
    }

    public Boolean delete(int id) {
        try {
            // Buscar el índice del rol con el id proporcionado
            for (int i = 0; i < listRols.getSize(); i++) {
                Rol currentRol = (Rol) listRols.get(i); // Se asume que `get` devuelve el objeto en el índice i
                if (currentRol.getId() == id) {
                    listRols.delete(i); // Elimina el rol en el índice encontrado
                    this.listRols = listAll(); // Refresca la lista
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Si no se encuentra el rol con el id especificado, devuelve false
        return false;
    }
    
    
}
