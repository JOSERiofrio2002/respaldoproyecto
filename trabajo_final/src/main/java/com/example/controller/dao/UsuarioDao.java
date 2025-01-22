package com.example.controller.dao;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Persona;
import com.example.models.Rol;
import com.example.models.Usuario;

public class UsuarioDao extends AdapterDao<Usuario> {
     // Atributos
    private Usuario user;
    private LinkedList listUsr;

    public UsuarioDao(){
        super(Usuario.class);
    }

    public Usuario getUsuario(){
        if(user == null){
            user = new Usuario();
        }
        return user;
    }

    public void setUsuario(Usuario r){
        this.user = r;
    }

    public LinkedList getListAll() {
        if(listUsr == null){
            this.listUsr = listAll();
        }
        return listUsr;
    }

    // CRUD
    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        user.setId(id);
        this.persist(this.user);
        this.listUsr = listAll();
        return true;
    }


    public Boolean update() throws Exception {
        this.merge(getUsuario(), getUsuario().getId()-1);
        this.listUsr = listAll();
        return true;
    }

    public Boolean delete(int id) throws Exception{
        LinkedList<Usuario> list = getListAll(); 
        Usuario persona = get(id); 
        if (persona != null) {
            list.delete(id); 
            // String info = g.toJson(list.toArray());
            // saveFile(info); 
            this.listUsr = getListAll();
            return true;
        } else {
            System.out.println("Usuario con id " + id + " no encontrada.");
            return false;
        }
    }
}
