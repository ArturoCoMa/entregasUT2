package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ejJDBC4 {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        System.out.println("Realizando conexión...");
        try (Connection cnn = PoolCnn.getConnection();
             PreparedStatement pstmt = cnn.prepareStatement("INSERT INTO juego (id, nombre, precio) VALUES (?,?,?)");
        ){
            System.out.println("Éxito");

            System.out.println("Introduce");
            System.out.println("ID:");
            int id = tec.nextInt();
            tec.nextLine();
            System.out.println("Nombre:");
            String nombre = tec.nextLine();
            System.out.println("Precio");
            double precio = tec.nextDouble();

            System.out.println("Realizando inserción de los nuevos datos...");

            pstmt.setInt(1,id);
            pstmt.setString(2,nombre);
            pstmt.setDouble(3,precio);
            pstmt.executeUpdate();
            System.out.println("Éxito");


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
