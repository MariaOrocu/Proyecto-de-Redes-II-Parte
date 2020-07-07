/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.ConexionMysql.getConnection;
import domain.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultListModel;
import org.apache.commons.codec.binary.Base64;

public class UsuarioData extends ConexionMysql {

    String secretKey = "Key";

    public boolean registra(Usuario usr) {

        PreparedStatement ps = null;
        Connection cn = getConnection();

        String query = "insert ignore into usuario_ftp (nombre_usuario, contrasenna, carpeta) values (?,?,?)";
        try {
            ps = cn.prepareStatement(query);
            ps.setString(1, usr.getNombreUsuario());
            ps.setString(2, usr.getContrasenna());
            ps.setString(3, usr.getCarpeta());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioData.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int login(Usuario usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        Connection con = getConnection();
        String decripted = "";
        if (usr.getNombreUsuario().equalsIgnoreCase("Admin") & usr.getContrasenna().equalsIgnoreCase("Admin")) {
            id = 1;
            return id;
        } else {
            String query = "SELECT nombre_usuario, contrasenna FROM usuario_ftp WHERE nombre_usuario = ?";
            try {
                ps = con.prepareStatement(query);
                ps.setString(1, usr.getNombreUsuario());
                rs = ps.executeQuery();
                if (rs.next()) {
                    decripted = descifrar("Key", rs.getString(2));
                    if (usr.getContrasenna().equals(decripted)) {
                        id = 2;
                    } else {
                        id = 0;
                    }
                }
                return id;
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioData.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }

    public static ArrayList<String> llenarUsuarios() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection();
        ArrayList<String> datos = new ArrayList<String>();
        String query = "select * from usuario_ftp";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos.add(rs.getString(2));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioData.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public static DefaultListModel llenarArchivos(String nombre) {
        DefaultListModel modelo = new DefaultListModel();
        File folder = new File("c:\\redes\\" + nombre + "\\");
        File[] listaFolder = folder.listFiles();
        if (listaFolder != null) {
            for (int i = 0; i < listaFolder.length; i++) {
                modelo.addElement(listaFolder[i].getName());
            }
        } else {
            modelo.addElement("El Usuario " + nombre + " no tiene archivos");
        }
        return modelo;
    }

    public static boolean copiarAchivos(String nombre, String desde, String hasta) throws FileNotFoundException, IOException {
        File folder = new File("c:\\redes\\" + nombre + "\\");
        File[] listaFolder = folder.listFiles();
        File since;
        File to;
        int length;
        InputStream inStream = null;
        OutputStream outStream = null;
        String valores = "c:\\Users\\jcast\\Documents\\" + nombre + "\\";
        File directorio = new File(valores);
        directorio.mkdir();
        if (listaFolder != null) {
            for (int i = 0; i < listaFolder.length; i++) {
                since = new File("c:\\redes\\" + nombre + "\\" + listaFolder[i].getName());
                to = new File(valores + listaFolder[i].getName());
                inStream = new FileInputStream(since);
                outStream = new FileOutputStream(to);
                byte[] buffer = new byte[1024];
                //int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

            }
            return true;
        } else {
            return false;
        }
    }

    public String cifrar(String key, String contra) {
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
}
