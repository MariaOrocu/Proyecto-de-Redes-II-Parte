package server;

import java.io.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerThread hilo = new ServerThread(4400);
        int id = 0;
        while (true) {
            try {
                hilo.iniciarServidor();
                Thread.sleep(2000);
                id++;

            } catch (Exception e) {

            }
        }
    }

}
