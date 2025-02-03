package com.example.controller.dao;


import java.io.FileWriter;
import java.io.IOException;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.controller.tda.list.Node;
import com.example.models.Token;
import com.google.gson.Gson;

public class TokenDao extends AdapterDao<Token>{

	private static final String FILE_PATH = "path/to/your/file.json";
	
	private Token tokn;
	private LinkedList listTokns;
	

	public TokenDao() {
		super(Token.class);
	}
	
	public Token getToken() {
		return this.tokn;
	}
	
	public void setTokn(Token tkn) {
		this.tokn = tkn;
	}
	
	public LinkedList getListTokns() {
		if(listTokns == null){
            this.listTokns = listAll();
        }
        return listTokns;
	}
	
	
	public Boolean save() throws Exception {
        Integer id = getListTokns().getSize()+1;
        tokn.setIdToken(id);
        
        
        this.persist(this.tokn);
        this.listTokns = listAll();
        return true;
    }
	
	public void delete(Object index) throws Exception{
			this.delete(index);
		actualizar_lista_Ids();
		this.listTokns = listAll();
	}
	
	// realizar logica para refresh token
	public Boolean refreshToken() throws Exception{
		return true;
	}
	
	private void actualizar_lista_Ids() throws Exception {
        int contador = 0; // Comenzar desde 1
        
        Node<Token> current = getListTokns().getHeader(); // Suponiendo que tienes un método para obtener la cabeza de la lista
        Token mensajero;
        
        while (current != null) {
        	contador++; // cuenta 1
            mensajero = current.getInfo(); // obtiene el objeto de NODO
            mensajero.setIdToken(contador); // actualiza el id del Objeto
            
        	current.setInfo(mensajero); // Asigna o guarda esa info en su respectivo Nodo
            current = current.getNext(); // Moverse al siguiente nodo
        }
       
        this.UpdateFile(listTokns); // Actualiza el archivo si es necesario
    }
	
	// Realizar metodo de busqueda por token, para comparar

	private void UpdateFile(LinkedList listTokns) {
		Gson gson = new Gson();
		String json = gson.toJson(listTokns.toArray());

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
}
