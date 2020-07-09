/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo implements Runnable {

    String nombreHilo;
    EjemplosVarios varios = new EjemplosVarios();

    public Hilo(String nombre) {
        nombreHilo = nombre;
    }

    //Punto de entrada del hilo
    //Los hilos comienzan a ejecutarse aquí
    @Override
    public void run() {
        System.out.println("Comenzando " + nombreHilo);
        try {
            varios.enviarEjemplos(nombreHilo);
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
