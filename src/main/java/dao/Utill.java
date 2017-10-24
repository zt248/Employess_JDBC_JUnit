package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utill  {

    private static final String DB_DRIVE = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_employees";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";


    public Connection getConnection()  {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVE);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERRORE");
        } catch (Exception e) {
          e.printStackTrace();
        }
        return connection;
    }
}
