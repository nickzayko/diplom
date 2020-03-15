package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDataBase {

    private static final String URL = "jdbc:mysql://http://192.168.20.100/8088/diploma_project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection conection;

    public static void main(String[] args) {
        Connection  connection = getConection();
        try {
            String statement = connection.getCatalog();
            System.out.println("dataBase: " + statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConection() {
        if (conection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conection = DriverManager.getConnection(URL, USER, PASSWORD);
                return conection;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conection;
    }

    public static void close() {
        if (conection != null) {
            try {
                conection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
