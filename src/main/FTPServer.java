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

        //FileInputStream fis = new FileInputStream();
        //UsuarioData data = new UsuarioData();
        //data.copiarAchivos("Antonio", "", "");
        EjemplosVarios varios = new EjemplosVarios();
        //String guarda = varios.cifrar("Key", "1234");
        //System.out.println("encriptado texto:" + varios.cifrar("Key", "1234") + ",fin");
        //System.out.println("desencriptado texto: " + varios.descifrar("Key", guarda));
        //varios.enviarEjemplos("Antonio");
        //varios.recibirAhi();
        //String secretKey = "jonathancastro.com";
        //varios.encryptedFile(secretKey, "C:\\redes\\Jonnsn.sql", "C:\\redes\\Jonnsn.enc");
        //System.out.println("encriptado");
        //varios.decryptedFile(secretKey, "C:\\redes\\Jonnsn.enc", "C:\\redes\\Jonnsn.sql");
        //System.out.println("desencriptado");
    }

}
