package com.sapient.programs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sapient.utils.DbUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetRecordsFromPersonsTable {

    public static void main(String[] args) {
        String sql = "select * from persons";

        try (Connection conn = DbUtil.createConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                log.debug("id = {}, name = {}", id, name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
