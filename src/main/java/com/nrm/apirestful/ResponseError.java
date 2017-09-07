/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrm.apirestful;

/**
 *CLASE QUE MANEJA LA EXCEPCIONES Y LOS MENSAJES DE ERROR
 * 
 * @author Nicolás
 */
public class ResponseError {
    private String message;
 
  public ResponseError(String message, String... args) {
    this.message = String.format(message, args);
  }
 
  public ResponseError(Exception e) {
    this.message = e.getMessage();
  }
 
  public String getMessage() {
    return this.message;
  }

}
