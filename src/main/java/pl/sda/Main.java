package pl.sda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Remigiusz Zudzin
 */
public class Main {

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/java6?serverTimezone=UTC",
                "root",
                "root");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

       Connection connection = getConnection();




    }

}
