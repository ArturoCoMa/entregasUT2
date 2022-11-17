//realiza una transacción que no se complete al provocar un rollback
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ejJDBC5 {
    public static void main(String[] args) {
        String sql = "INSERT INTO juego (id, nombre, precio) VALUES (?,?,?)";
        int id = 1;
        String nombre = "Sonic";
        double precio = 20.0;
        Connection cnn = null;
        PreparedStatement pstmt = null;

        try{
            System.out.println("Realizando conexión...");
            cnn = PoolCnn.getConnection();
            System.out.println("Éxito");

            System.out.println("Iniciando transacción...");
            cnn.setAutoCommit(false);
            pstmt = cnn.prepareStatement(sql);
            //aquí deberia fallas pues esta id ya existe.
            pstmt.setInt(1,id);
            pstmt.setString(2,nombre);
            pstmt.setDouble(3, precio);
            pstmt.executeUpdate();

            cnn.commit();
            System.out.println("Éxito en la transacción");
        } catch (SQLException e) {
            System.out.println("Transacción no realizada");
            try {
                cnn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                cnn.setAutoCommit(true);
                if(cnn!=null)
                    cnn.close();
                if(pstmt!=null)
                    pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
