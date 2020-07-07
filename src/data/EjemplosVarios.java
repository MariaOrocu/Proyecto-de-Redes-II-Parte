/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.management.openmbean.InvalidKeyException;
import org.apache.commons.codec.binary.Base64;

/**
 *
 *
 */
public class EjemplosVarios {

    String secretKey = "Key";

    public void enviarEjemplos(String nombre) throws UnknownHostException, IOException {
        String directory = "C:\\redes\\" + nombre + "\\";
        String huesped = "c:\\Users\\jcast\\Documents\\" + nombre + "\\";
        String hostDomain = "192.168.1.5";
        int port = 4400;
        File folder = new File(huesped);
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        InetAddress ip = InetAddress.getLocalHost();

        File[] files = new File(directory).listFiles();

        Socket socket = new Socket(ip, 4400);
        System.out.println("bien");

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeChar('B');
        dos.writeUTF(nombre);
        dos.writeInt(files.length);

        for (File file : files) {
            long length = file.length();
            dos.writeLong(length);

            String name = file.getName();
            dos.writeUTF(name);

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int theByte = 0;
            while ((theByte = bis.read()) != -1) {
                bos.write(theByte);
            }

            bis.close();
        }

        dos.close();
        socket.close();
    }

    public void recibirAhi(String nombre) throws IOException {
        while (true) {
            String dirPath = "c:\\Users\\jcast\\Documents\\" + nombre;

            ServerSocket serverSocket = new ServerSocket(4400);
            Socket socket = serverSocket.accept();

            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(bis);

            int filesCount = dis.readInt();
            File[] files = new File[filesCount];

            for (int i = 0; i < filesCount; i++) {
                long fileLength = dis.readLong();
                String fileName = dis.readUTF();

                files[i] = new File(dirPath + "\\" + fileName);

                FileOutputStream fos = new FileOutputStream(files[i]);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                for (int j = 0; j < fileLength; j++) {
                    bos.write(bis.read());
                }

                bos.close();
            }

            dis.close();
        }
    }

    public String cifrar(String key, String contra) throws Exception {
        String encriptado = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyPass = md5.digest(key.getBytes("utf-8"));
            byte[] bytesKey = Arrays.copyOf(keyPass, 24);
            SecretKey llave = new SecretKeySpec(bytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, llave);
            byte[] plainTextBytes = contra.getBytes("utf-8");
            byte[] buffer = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes;
            base64Bytes = Base64.encodeBase64(buffer);
            encriptado = new String(base64Bytes);
        } catch (Exception e) {
            System.out.println(e);
        }

        return encriptado;
    }

    public String descifrar(String key, String contra) {
        String desencriptado = "";
        try {
            byte[] message = Base64.decodeBase64(contra.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey keys = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, keys);
            byte[] plainText = decipher.doFinal(message);
            desencriptado = new String(plainText, "UTF-8");

        } catch (Exception e) {
            System.out.println(e);
        }
        return desencriptado;
    }

    public void encryptedFile(String secretKey, String fileInputPath, String fileOutPath) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException, java.security.InvalidKeyException {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        File fileInput = new File(fileInputPath);
        FileInputStream inputStream = new FileInputStream(fileInput);
        byte[] inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        File fileEncryptOut = new File(fileOutPath);
        FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully encrypted!");
        System.out.println("New File: " + fileOutPath);
    }

    public void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException, java.security.InvalidKeyException {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        File fileInput = new File(fileInputPath);
        FileInputStream inputStream = new FileInputStream(fileInput);
        byte[] inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        File fileEncryptOut = new File(fileOutPath);
        FileOutputStream outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully decrypted!");
        System.out.println("New File: " + fileOutPath);
    }

}
