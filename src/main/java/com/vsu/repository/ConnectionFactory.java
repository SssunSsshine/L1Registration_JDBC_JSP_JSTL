package com.vsu.repository;

import com.vsu.exception.DBException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");
            if (ds == null) {
                throw new RuntimeException("Data source not found");
            }
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new DBException(e);
        }
    }
}
