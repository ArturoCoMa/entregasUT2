//haz uso a lo largo del proyecto de un agrupamiento (pool) de conexiones
package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolCnn {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:postgresql://localhost:5432/arturo");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public PoolCnn() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
