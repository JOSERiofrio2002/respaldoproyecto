package com.example.controller.dao.services;


import com.example.controller.dao.UsuarioDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Usuario;

public class UsuarioService {

    private UsuarioDao userDao;

    public UsuarioService(){
        userDao = new UsuarioDao();
    }
    public Boolean save() throws Exception{
        return userDao.save();
    }
    public Boolean update() throws Exception{
        return userDao.update();
    }
    public Boolean delete(Integer id) throws Exception {
//        return userDao.delete(id);
        return userDao.delete(id.intValue());
    }


    public LinkedList listAll(){
        return userDao.getListAll();

    }

    public Usuario getUsuario(){
        return userDao.getUsuario();
    }

    public void setUsuario( Usuario user){
        userDao.setUsuario(user);
    }

    public Usuario get(Integer id) throws Exception {
        return userDao.get(id);
    }

    public Usuario findUserbyEmail(String email) throws Exception{
    	return userDao.getUsuariobyEmail(email);
    }

//    public Administrador findPersonabyEmail(String text_email) throws Exception{
//    	return userDao.getPersonabyEmail(text_email);
//    }

 // Metodo booleano que permite verificar tal persona tenga un usuario
    public Boolean existAsignacion() throws Exception{
    	return userDao.existAsignacion();
    }

    // Metodo que permite verificar si el usuario a asignar ya tiene otra persona...
    // Es decir evitar que existan 2 personas con el mismo usuario o viceversa
    public boolean existOtherUser(int idPersona) throws Exception{
    	return userDao.existOtherUser(idPersona);
    }

}
