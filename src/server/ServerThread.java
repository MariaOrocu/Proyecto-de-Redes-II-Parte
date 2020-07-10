/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import data.EjemplosVarios;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class ServerThread implements Runnable {

    private ServerSocket server = null;
    private EjemplosVarios ejemplosVarios;
    private static Key key;

    public ServerThread(int puerto) throws IOException {
        ejemplosVarios = new EjemplosVarios();
        server = new ServerSocket(puerto);
        System.out.println("Esperando recepcion de archivos...");
    }

    public void iniciarServidor() throws InterruptedException {
        while (true) {

            try {
                // Creamos el socket que atendera el servidor
                Socket cliente = server.accept();
                String ruta = "c:\\redes\\";
                // Creamos flujo de entrada para leer los datos que envia el cliente
                BufferedInputStream bis = new BufferedInputStream(cliente.getInputStream());
                DataInputStream dis = new DataInputStream(bis);

                // Obtenemos el nombre del archivo
                char type = dis.readChar();
                if (type == 'A') {
                    iniciarEncriptacion();
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    String nombreArchivo = dis.readUTF();
                    System.out.println("ver " + nombreArchivo.toString());
                    int tam = dis.readInt();
                    String nombreUsuario = dis.readUTF();
                    ruta += nombreUsuario + "\\";
                    File directorio = new File(ruta);
                    directorio.mkdir();
                    FileOutputStream fos = new FileOutputStream(ruta + nombreArchivo);
                    BufferedOutputStream out = new BufferedOutputStream(fos);
                    BufferedInputStream in = new BufferedInputStream(cliente.getInputStream());

                    byte[] buff = new byte[tam];

                    // Obtenemos el archivo mediante la lectura de bytes enviados
                    for (int i = 0; i < buff.length; i++) {
                        buff[i] = (byte) in.read();
                    }
                    String valoresEncriptados = Base64.encode(buff);
                    byte[] bytesEn = Base64.decode(valoresEncriptados.replace("\n", ""));
                    byte[] des = cipher.doFinal(bytesEn);
                    //buff = pr.decrypt(str, "boooooooooom!!!!").getBytes("UTF-8");
                    out.write(des);

                    // Cerramos flujos
                    out.flush();
                    in.close();
                    out.close();
                    cliente.close();

                    System.out.println("Archivo guardado"
                            + " " + nombreArchivo);
                }
                if (type == 'B') {

                    String dirPath = "c:\\redes\\";
                    String nombre = dis.readUTF();
                    int filesCount = dis.readInt();
                    dirPath += nombre + "\\";
                    File directorio = new File(dirPath);
                    directorio.mkdir();
                    File[] files = new File[filesCount];

                    for (int i = 0; i < filesCount; i++) {
                        long fileLength = dis.readLong();
                        String fileName = dis.readUTF();

                        files[i] = new File(dirPath + "\\" + fileName);

                        FileOutputStream fos = new FileOutputStream(files[i]);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        //BufferedInputStream bis = new BufferedInputStream(cliente.getInputStream());
                        for (int j = 0; j < fileLength; j++) {
                            bos.write(bis.read());

                        }
                        bos.close();
                    }
                    System.out.println("Directorio actualizado para: " + nombre);
                    dis.close();
                }
            } catch (Exception e) {
                System.out.println("Recibir: " + e.toString());
            }
            Thread.sleep(1000);
        }
    }

    @Override
    public void run() {

    }

    public void iniciarEncriptacion() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");

        generator.init(128);
        key = generator.generateKey();
        //obtiene los bytes del 0 a 15
        key = new SecretKeySpec("redes2JonatMaria".getBytes(), 0, 16, "AES");

    }
}
