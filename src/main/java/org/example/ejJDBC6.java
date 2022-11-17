//utiliza al menos una función y un procedimiento. Adjunta el código de las funciones y procedimientos también.

package com.dam2.ut2;

import java.sql.*;
import java.util.Scanner;

public class ejJDBC6 {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        Connection cnn = null;
        CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            System.out.println("Realizando conexión...");
            cnn = PoolCnn.getConnection();

            //ejecución de la function
            System.out.println("Conteo de juegos.");
            String sqlFunc = "SELECT public.cuentaJuegos()";
            pstmt = cnn.prepareStatement(sqlFunc, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();

            rs.first();
            if (rs.wasNull())
                System.out.println("Sin juegos");
            else {
                int cont = rs.getInt(1);
                System.out.println("Total juegos: " + cont);
            }

            //ejecución del procedure
            System.out.println("¿Qué juego quieres borrar? Escribe su ID");
            int id = tec.nextInt();
            String sqlCall = "CALL public.borrajuegos(?)";
            cstmt = cnn.prepareCall(sqlCall);
            cstmt.setInt(1, id);
            cstmt.execute();
            System.out.println("Juego con id "+id+" borrado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(cnn!=null)
                    cnn.close();

                if(cstmt!=null)
                    cstmt.close();

                if(pstmt!=null)
                    pstmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
