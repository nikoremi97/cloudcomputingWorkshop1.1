/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrm.apirestful;

/**
 *
 * @author Nicolás
 * 
 * REFERENCIAS: http://www.mscharhag.com/java/building-rest-api-with-spark
 */
import static spark.Spark.*;
public class Main {
    public final static void main(String[] args){
        new LigaController(new Liga());
    }
}
