/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMysql {

    // create a function to connect with mysql database
    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://163.178.107.10:3306/redes_B41654_B55170?useSSL=false", "laboratorios", "UCRSA.118");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return con;
    }

}
