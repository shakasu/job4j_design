package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class HelloJDBC {
    private static final Logger LOG = LoggerFactory.getLogger(HelloJDBC.class.getName());

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/car_catalog";
        String username = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM car");
            while (rs.next()) {
                System.out.printf("%s", rs.getString("name"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
