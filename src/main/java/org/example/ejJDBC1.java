//Crea un proyecto JDBC que genere una base de datos con tu nombre y, al menos,
//una tabla para el elemento con el que estés trabajando (discos, libros, videojuegos, etc).
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ejJDBC1 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String url2 = "jdbc:postgresql://localhost:5432/arturo";
        String sql = "CREATE DATABASE arturo; ";

        System.out.println("Realizando conexión...");

        try(Connection cnn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = cnn.createStatement();
        ) {
            System.out.println("Éxito");

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection cnn = DriverManager.getConnection(url2, "root", "root");
            Statement stmt = cnn.createStatement();
        ) {
            System.out.println("Generando tabla...");
            sql = "CREATE TABLE juego(id int primary key , nombre varchar(255), precio numeric(3,1));";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Éxito");



    }
}
