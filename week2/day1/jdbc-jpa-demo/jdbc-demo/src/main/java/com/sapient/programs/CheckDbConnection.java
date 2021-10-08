package com.sapient.programs;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckDbConnection {
    public static void main(String[] args) throws Exception {
        log.debug("List of drivers registered with DriverManager");
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver dr = drivers.nextElement();
            log.debug("{}", dr.getClass().getName());
        }
        // http://www.vinod.co:80/blog
        // https://www.vinod.co:443/blog

        String url = "jdbc:mysql://localhost:3306/trainingdb";
        // Oracle --> jdbc:oracle:thin:@localhost:1521:ORCL
        // H2 --> jdbc:h2:tcp://localhost/~/trainingdb
        // MSSQL --> jdbc:mssql://localhost:1433/trainingdb

        String user = "root";
        String password = "Welcome#123";

        Connection conn = DriverManager.getConnection(url, user, password);
        log.debug("conn is of type {}", conn.getClass().getName());

        conn.close();
    }
}
