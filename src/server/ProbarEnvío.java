/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import data.EjemplosVarios;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProbarEnvío {

    public static void main(String[] args) throws IOException, InterruptedException {
        EjemplosVarios varios = new EjemplosVarios();
        Scanner sc = new Scanner(System.in);
        System.out.println("ingrese el nombre");

        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        varios.enviarEjemplos("" + sc);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            Thread.sleep(2000);
        }

    }
}
