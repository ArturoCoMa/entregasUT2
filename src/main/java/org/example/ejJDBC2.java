//sobre un ResultSet de todo el contenido de la tabla realiza al
// menos una inserción, una modificación y un borrado utilizando,
// al menos, 5 métodos de desplazamiento a lo largo del ResultSet
package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ejJDBC2 {
    public static void main(String[] args) {
        String sql = "SELECT * FROM juego";
        System.out.println("Conectando con base de datos...");
        try(Connection cnn = PoolCnn.getConnection();
            Statement stmt = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
        ){
            System.out.println("Éxito");


            System.out.println("Generando nueva inserción...");
            rs.moveToInsertRow();
            rs.updateInt(1,1);
            rs.updateString(2,"God of war");
            rs.updateDouble(3, 50.0);
            rs.insertRow();
            System.out.println("Éxito");

            System.out.println("Generando nueva inserción...");
            rs.moveToInsertRow();
            rs.updateInt(1,2);
            rs.updateString(2,"Okami");
            rs.updateDouble(3, 20.0);
            rs.insertRow();
            System.out.println("Éxito");

            System.out.println("Moviendonos al primer registro para modificar su nombre por el de Sonic...");
            rs.beforeFirst();
            rs.next();
            rs.updateString("nombre", "Sonic");
            rs.updateRow();
            System.out.println("Éxito");


            System.out.println("Moviendonos a la posición dos de la tabla para borrarla...");
            rs.absolute(2);
            rs.deleteRow();
            System.out.println("Éxito");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
