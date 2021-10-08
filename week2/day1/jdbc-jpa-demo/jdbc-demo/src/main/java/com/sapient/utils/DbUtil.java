package com.sapient.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public final class DbUtil {

    private DbUtil() {
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc-info");
        String driverClassName = rb.getString("jdbc.connection.driverClassName");
        String url = rb.getString("jdbc.connection.url");
        String username = rb.getString("jdbc.connection.username");
        String password = rb.getString("jdbc.connection.password");
        Class.forName(driverClassName); // in recent versions this happens implicitly
        return DriverManager.getConnection(url, username, password);
    }
}
