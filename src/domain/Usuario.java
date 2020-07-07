/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

public class Usuario {

    private int id;
    private String nombreUsuario;
    private String contrasenna;
    private String carpeta;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String contrasenna, String carpeta) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenna = contrasenna;
        this.carpeta = carpeta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasenna=" + contrasenna + ", carpeta=" + carpeta + '}';
    }
}
