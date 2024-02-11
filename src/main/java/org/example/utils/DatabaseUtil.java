package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseUtil {
    private static final String DATABASE_PROPERTIES = "db";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle(DATABASE_PROPERTIES);
        String url = resource.getString(URL_KEY);
        String username = resource.getString(USERNAME_KEY);
        String password = resource.getString(PASSWORD_KEY);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Could not find database driver class", ex);
        } catch (SQLException ex) {
            throw new RuntimeException("An error occurred. Maybe user/password is invalid", ex);
        }
    }
}