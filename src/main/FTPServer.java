package main;

import GUI.Login;
import data.UsuarioData;
import java.io.*;
import java.net.*;
import data.EjemplosVarios;

public class FTPServer {

    public static void main(String[] args) throws Exception {
        Login login = new Login();
        login.setVisible(true);
        byte a = new Byte("1");
        //System.out.println(a);
        /*EjemplosVarios varios = new EjemplosVarios();
        System.out.println(varios.cifrar("Key", String.valueOf(a)));
        String enc = varios.cifrar("Key", String.valueOf(a));
        System.out.println("des " + varios.descifrar("Key", enc));
         */
    }

}
