package com.sapient.programs;

import java.sql.Connection;
import java.sql.Statement;

import com.sapient.utils.DbUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateTable {
    public static void main(String[] args) {
        String sql = "create table persons (id int primary key auto_increment, name varchar(50))";
        log.debug(sql);

        try (Connection conn = DbUtil.createConnection(); Statement stmt = conn.createStatement();) {

            stmt.execute(sql);
            log.debug("table persons created successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
