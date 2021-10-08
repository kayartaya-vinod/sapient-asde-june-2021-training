package com.sapient.programs;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sapient.utils.DbUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsertPersonRecords {

    public static void main(String[] args) {

        String sql = "insert into persons (name) values (?)";

        try (Connection conn = DbUtil.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, "Shyam");
            stmt.execute();
            log.debug("new record for persons table inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
