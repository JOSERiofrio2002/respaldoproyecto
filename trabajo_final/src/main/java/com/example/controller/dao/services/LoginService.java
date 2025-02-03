package com.example.controller.dao.services;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import com.example.controller.dao.TokenDao;
import com.example.models.Token;
import com.example.models.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class LoginService {

//	private UsuarioDao userDAO;
	private UsuarioService userService;
	private TokenDao tknDAO;

	private static final String SECRET_KEY = "asdjoijqwijIASJOJDAJoijwiejqeojasjdoqpomzxcmllAasd"; // Esto debe ser más seguro en un entorno real

	public LoginService() {
		this.userService = new UsuarioService();
		this.tknDAO = new TokenDao();
	}

	public String login(String email, String password) throws Exception {

		// trae el usuario para verificar o comparar la contrasenia en bdd, con la contrasenia que envia
		Usuario user = userService.findUserbyEmail(email);

		if (user == null) {
			return "Usuario no existe";
		}


		// Verificar la contraseña hasheada
		if (BCrypt.checkpw(password, user.getContrasenia())) {
			// Si las credenciales son correctas, generar el JWT
//			System.out.println("Contrasenia correcta");
//			System.out.println("usuario : "+user.getCorreo());
			return generateJWT(user);
		} else {
			return "Contraseña incorrecta";
		}
	}

	private String generateJWT(Usuario user) throws Exception {

		Token tkn = new Token();


		// Obtener la fecha y hora actual
		long now = System.currentTimeMillis(); // opcional usar Calendar

		SimpleDateFormat formatter_fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fecha_em = new Date(now);
		Date fecha_exp = new Date(now+ 3600000);


		Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		Map claims = new HashMap<>();
		String msg;

		// construimos un map
		claims.put("email", user.getCorreo());

		// OJO Error al momento de traer datos de la persona
		//		Administrador personaAdmin = userService.findPersonabyEmail(user.getCorreo());
		//		claims.put("nombres completos", personaAdmin.getNombre()+" "+personaAdmin.getApellido());
		//		Error del Administrador
		/*
		 * 	"msg": "tester",
			"error Mesag": "Cannot invoke \"com.example.controller.dao.AdministradorDao.getlistAll()\" because \"this.personaDao\" is null",
			"data": "Ocurrio un error null",
			"error Localized": "Cannot invoke \"com.example.controller.dao.AdministradorDao.getlistAll()\" because \"this.personaDao\" is null"
		 */

		claims.put("rol", user.getRole().getNombre());

		// crear un token (objeto) y guardar a la base de dats

		// Generar el JWT con información sobre el usuario
		// header -> algoritmo
		// payload -> user, id, role, carga util, fecha
		// signature -> firma, clae secreta firmaca con algoritmo HS26
		try {

			String tokn = Jwts.builder().setSubject(String.valueOf(user.getId())) // ID del usuario como "subject" (sujeto)
					.setClaims(claims)
					.setIssuedAt(new Date(now)) // Fecha de emisión
					.setExpiration(new Date(now + 3600000)) // El token expira en 1 hora (3600000 segundos)
					.signWith(key, SignatureAlgorithm.HS256) // Firma del JWT con la clave secreta
					.compact();

//			System.out.println("tokn: "+tokn);
			msg = tokn;

			tkn.setIdUsr(user.getId());
			tkn.setToken(tokn);
        
			

			tkn.setValid(true); // validar el tokn

			tknDAO.setTokn(tkn);
			tknDAO.save(); // Guarda el token en bdd

		} catch (Exception e) {
			// TODO: handle exception
			msg = "Error en generate Token, CAUSA: "+e.getCause()+"\nMessage Localized: "+e.getLocalizedMessage()+" \nMessage: "+e.getMessage();

		}

		return msg;
	} 

	// metodo para refrescar el token

	// Metodo para logout
	public boolean logout(int idToken) {
		Token tkn;
		boolean flag = false;
		try {
			tkn = tknDAO.get(idToken);

			//Verifico si el token existe en bdd
			if (tkn != null) {
				tknDAO.setTokn(tkn);
				tknDAO.delete(tkn); // elimina
				flag = true; // significa que se elimino correctamente
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ocurrio un error en el metodo de logout");
			e.printStackTrace(); // imprime el seguimiento traz de error
			return false;
		}

		return flag;
	}

}
