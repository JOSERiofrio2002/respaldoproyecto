package com.example.controller.dao;

import org.mindrot.jbcrypt.BCrypt;

import com.example.controller.dao.implement.AdapterDao;
import com.example.controller.tda.list.LinkedList;
import com.example.models.Usuario;


public class UsuarioDao extends AdapterDao<Usuario> {
     // Atributos
    private Usuario user;
    private LinkedList listUsr;

    public UsuarioDao(){
        super(Usuario.class);
    }

    // Unica Instancia
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

        // hashear contrasenia
        user.setContrasenia(hashPasswd(user.getContrasenia()));

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

    // Metodo get
    // Traer el objeto usuario por el id
	@Override
	public Usuario get(Integer id) throws Exception {
		LinkedList<Usuario> listUsers = getListAll();
		Usuario user = null;

		for (Usuario usuario : listUsers.toArray()) {
			if(usuario.getId().compareTo(id) == 0) {
				user = usuario;
				break;
			}
		}

		return user;

	}


    // Metodo que me permite hasear contrasenia
    private String hashPasswd(String pwd) {

    	String salt_pwd = BCrypt.gensalt(5); // genSalt -> establece el nivel de seguridad, entre mas grande el numero, mas seguro mas lento de procesar
    	return BCrypt.hashpw(pwd, salt_pwd); // Devuelve el hash
    }

    // Metodo que me permite verificar si dos contrasenias son iguales
    // Necesitaremos la contrasenia que envia el user, y la contrasenia almacenada en bdd para comparar
    public boolean verifyPwd(String pwd_usr, String pwd_hash_usr) {
    	return BCrypt.checkpw(pwd_usr, pwd_hash_usr);
    }

    public Usuario getUsuariobyEmail(String email) throws Exception{
    	// Metodos de ordenacion
    	// Metodo de Busqueda
    	Usuario persona = null;
    	LinkedList listita = listAll();
    	if(!listAll().isEmpty()) {
    		Usuario[] aux = (Usuario[]) listita.toArray();

    		for (Usuario element : aux) {
    			// Si el apellido empieza con las letras del texto que tiene como parametro
				if (element.getCorreo().equals(email)) {
					persona = element;
				}
			}
    	}

    	return persona;
    }

    /*
    public Administrador getPersonabyEmail(String email) throws Exception{
    	Administrador person = null;
    	LinkedList listPersona = personaDao.getlistAll();

    	if(!personaDao.getlistAll().isEmpty()) {
    		Administrador[] aux = (Administrador[]) listPersona.toArray();

    		for (int i = 0; i < aux.length; i++) {
    			// Si el apellido empieza con las letras del texto que tiene como parametro
				if (aux[i].getEmail().equals(email)) {
					person = aux[i];
					break;
				}
			}
    	}

    	return person;
    }*/


    // Metodo booleano que permite verificar tal persona tenga un usuario
    public Boolean existAsignacion() throws Exception{
    	// De la lista verifica si este usuario contien
    	return this.user.getIdPersona() != null;
    }

    // Metodo que permite verificar si el usuario a asignar ya tiene otra persona...
    // Es decir evitar que existan 2 personas con el mismo usuario o viceversa
    public boolean existOtherUser(int idPersona) throws Exception{
    	// Obtengo la lista de los usuarios ordenada
    	LinkedList list = ordenarListaQuickSort(1);
    	Usuario[] list_users = (Usuario[]) list.toArray();

    	// Recoroo o busco por busqueda lineal binaria
    	// previa ordenacion (quicksort)
    	// Si el IdPersona que estoy queriendo verificar se encuentra de la mitad de la lista ordenada hacia adelante
    	// if ternario
    	try {
    		int indiceInicio = (idPersona > list_users[list_users.length/2].getIdPersona()) ? list_users.length / 2 : 0 ;
    		int indiceFinal = (idPersona > list_users[list_users.length/2].getIdPersona()) ? list_users.length : list_users.length / 2 ;
    		for (int i = indiceInicio; i < indiceFinal; i++) {
    			return idPersona == list_users[i].getIdPersona();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en la verificacion del metodo existOtherUser");
			System.out.println(e.getCause()+", mensaje "+e.getMessage());
		}
    	return false;
    }

    // Ordenacion QuickSort de la lista de Usuario por IdPersona
   /**
    * Metodo de Ordenacion QuickSort, segun el id
    * @param tipo_orden -> asc 1, desc 0
    * @param atributo
    * @return
    * @throws Exception
    */
	public LinkedList ordenarListaQuickSort(int tipo_orden) throws Exception {

		LinkedList list = getListAll();
		Usuario[] array = (Usuario[]) list.toArray();

		list.reset();

		try {
			// Método QuickSort para ordenar el arreglo
			// Parametros , array, valor indic bajo, valor indic alto (ultimo), tipo orden, atributo
			quickSort(array, 0, array.length - 1, tipo_orden);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en la ordenacion de la lista QuickSort");
			System.out.println(e.getCause()+", mensaje "+e.getMessage());
		}


		list.toList(array);

		return list;
	}

	/**
	 * Funcion sin valor de retorno que realiza la ordenacion de una lista por
	 * tipo de orden
	 * @param array arreglo del Objeto a ordenar
	 * @param bajo valor bajo del array principio
	 * @param alto valor alto del array final
	 * @param type_order tipo de orden 1 asc, 0 desc
	 */
	private void quickSort(Usuario[] array, int bajo, int alto, int type_order) {
		// Controlar de modo que debido a la recursion el metodo se acaba cuando bajo es >= alto
		if (bajo < alto) {

			// Variable indice del pivote
			// Particiona y ordena
			int pi = particion_array(array, bajo, alto, type_order);

			// Recursivamente ordenamos las dos sublistas
			quickSort(array, bajo, pi - 1, type_order); // Sublista izquierda
			quickSort(array, pi + 1, alto, type_order); // Sublista derecha
		}
	}

	// Método para realizar la partición del arreglo, retorna indice del pivote
	private int particion_array(Usuario[] array, int bajo, int alto, int type_order) {
		// Tomamos el último elemento como pivote
		// Para comparar con los valores bajos
		Usuario pivote = array[alto];

		// Índice para el menor elemento
		int i = bajo - 1;

		// Comparamos cada elemento con el pivote
		// desde el primer elemento hasta el ultimo elemento de la lista
		for (int j = bajo; j < alto; j++) {
			// Compara los elementos y verifica, ojo
			// Recordemos que para verificar que el elemento pivote es mayor que el objeto primero
			// debo comparar primero el pivote
			if (verify(pivote,array[j], type_order)) {
				i++; // Aumentamos el índice de menor elemento para seguir comparando con el pivote

				// Intercambiamos elementos ya que existe que el elemento efectivamente es mayor al pivote
				intercambio(array, i, j);

			}
		}

		// El pivote se ubica en su posición natural
		intercambio(array, i + 1, alto);

		return i + 1; // Devolvemos el índice del pivote
	}

	private void intercambio(Usuario[] arr, int index1, int index2) {
		Usuario temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	private Boolean verify(Usuario a, Usuario b, Integer type_order) {
		if (type_order == 1) { // Ascendente IdPersona
			return a.getIdPersona().intValue() > b.getIdPersona().intValue();
		} else {
			return a.getIdPersona().intValue() < b.getIdPersona().intValue();
		}
//		return false;
	}
}
