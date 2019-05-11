package pl.sda;

import java.sql.*;

/**
 * @author Remigiusz Zudzin
 */
public class StatementExample {

    final Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/java6?serverTimezone=UTC",
            "root",
            "root");

    public StatementExample() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE Employee(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "salary int, " +
                "primary key (id) )");
    }

    public void fillTableWith5Employees() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO Employee (name, salary) VALUES (?, ?)");
        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "Jan Kowalski");
            preparedStatement.setInt(2, 3000+ (i*500));
            preparedStatement.executeUpdate();
        }
    }

    public void deleteFromTableEmployees(int value) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM Employee WHERE salary = " + value);
        preparedStatement.execute();
    }

    public void deleteTable(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("DROP TABLE " + name);
        preparedStatement.execute();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StatementExample statementExample = new StatementExample();
//        statementExample.createTable();
//        statementExample.fillTableWith5Employees();
//        statementExample.deleteFromTableEmployees(4000);
        statementExample.deleteTable("employee");
    }

}
