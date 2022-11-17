//realiza un ejemplo de código susceptible de sufrir ataque por inyección de SQL

package com.dam2.ut2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ejJDBC3 {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        System.out.println("Realizando conexión...");
        try (Connection conexion = PoolCnn.getConnection();
             Statement stmt = conexion.createStatement();
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

            System.out.println("Insertando nuevo juego...");
            String sql = "INSERT INTO juego (id, nombre, precio) VALUES ("+id+",'"+nombre+"',"+precio+")";
            stmt.executeUpdate(sql);
            System.out.println("Éxito");


        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
