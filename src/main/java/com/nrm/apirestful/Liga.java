/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrm.apirestful;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolás
 */
public class Liga {

    private HashMap<String, Equipo> equipos;

    /**
     * Constructor de la clase Liga que lee un archivo .properties para sacar la info de los equipos
     */
    public Liga() {
        equipos = new HashMap<String, Equipo>();
        FileInputStream fileInput = null;
        try {
            File file = new File("C:\\Users\\Nicolás\\Documents\\NetBeansProjects\\apirestful\\docs\\equipos.properties");

            fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                String[] contenido = value.split(";");
                System.out.println(key + ": " + value);
                Equipo equipito = new Equipo(contenido[0], contenido[1], contenido[2], contenido[3]);
                equipos.put(contenido[0], equipito);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException ex) {
                    Logger.getLogger(Liga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * retorna la estructura de datos donde se guarda la info de los equipos de Liga
     * @return equipos 
     */
    public HashMap getEquipos() {

        return equipos;
    }

    /**
     * Retorna los equipos de liga en tipo String
     * //No hace mucho
     * @return 
     */
    public String nombreEquipos() {

        String nomEq = "";

        for (HashMap.Entry<String, Equipo> entry : equipos.entrySet()) {
            String key = entry.getKey();
            Equipo equipo = entry.getValue();
            // do something with key and/or tab
            nomEq+=equipo.getNombre()+"\n";
            System.out.print(nomEq);
        }
        return nomEq;

    }
    /**
     * Retorna el equipo específico según el ID
     * @param id
     * @return Equipo
     */
    

    public Equipo getEquipo(String id) {
        Equipo equipito=equipos.get(id);
        return equipito;
    }
    
    /**
     * Retorna el equipo al cual s ele ha modificado la info
     * @param id
     * @param nombre
     * @param fundacion
     * @param titulos
     * @return Equipo 
     */

    public Equipo setEquipo(String id, String nombre, String fundacion, String titulos) {
        Equipo modificado=equipos.get(id);
        modificado.setNombre(nombre);
        modificado.setNombre(fundacion);
        
        modificado.setNombre(titulos);
        equipos.replace(id, modificado);
        return modificado;
        
    }

    /**
     * Crea un nuevo equipo y lo retorna
     * @param id
     * @param nombre
     * @param fundacion
     * @param titulos
     * @return Equipo
     */
    public Equipo createEquipo(String id,String nombre, String fundacion, String titulos) {
        Equipo elnuevo=new Equipo(id, nombre, fundacion, titulos);
        equipos.put(id, elnuevo);
        return elnuevo;
    }

    /**
     * Retorna el equipo que ha sido eliminado
     * @param id
     * @return Equipo
     */
    public Equipo deleteEquipo(String id) {
        
        Equipo eliminado=equipos.get(id);
        equipos.remove(id);
        return eliminado;
    }

}
