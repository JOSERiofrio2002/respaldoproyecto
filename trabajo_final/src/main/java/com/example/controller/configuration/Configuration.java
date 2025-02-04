package com.example.controller.configuration;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Esta es una clase de Configuracion que se aplica a la aplicacion
 * que tenga el URI myapp, es decir: localhost:8080/myapp/*..
 */
@ApplicationPath("myapp")
public class Configuration extends Application{

}
