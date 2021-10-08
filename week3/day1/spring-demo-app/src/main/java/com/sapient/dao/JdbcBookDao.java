package com.sapient.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.sapient.entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
// @Component // -> has these variants:
// @Service --> used for classes that belong to the service layer
@Repository // --> used for classes that belong to the DAO layer
// @Controller --> used for classes in the Presentation/web layer
// @RestController --> used for classes in the Presentation/web layer
// @Configuration --> used for classes that define spring configuration
// @Component --> used for any class that has no significance in any layer
// @Scope("prototype")
@Lazy
public class JdbcBookDao implements BookDao {

    // dependency resolved by spring, by looking into the list of beans avaliable in
    // the spring container.
    // First spring looks for a bean named "ds", and if not found, looks for bean of
    // type "DataSource"
    @Autowired
    DataSource ds;

    public JdbcBookDao() {
        log.debug("JdbcBookDao constructor called, and ds is {}", ds);
    }

   

    @Override
    public Book getById(Integer id) {
        String sql = "select * from books where id = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book b = new Book();
                    b.setId(id);
                    b.setTitle(rs.getString("title"));
                    b.setAuthor(rs.getString("author"));
                    b.setPublisher(rs.getString("publisher"));
                    b.setPrice(rs.getDouble("price"));
                    return b;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
