package pl.sda;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Remigiusz Zudzin
 */
public class StatementExample {

    final Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/java6?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true",
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

    public void fillTableWith5EmployeesUsingPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO Employee (name, salary) VALUES (?, ?)");
        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "Jan Kowalski");
            preparedStatement.setInt(2, 3000+ (i*500));
            preparedStatement.executeUpdate();
        }
    }

    public void deleteFromTableEmployeesUsingPreparedStatement(int value) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM Employee WHERE salary = " + value);
        preparedStatement.execute();
    }

    public void deleteTable(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("DROP TABLE " + name);
        preparedStatement.execute();
    }

    public void tests() throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT id, name, productionYear FROM movies");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int productionYear = resultSet.getInt(3);
            System.out.println("id = " + id + " name = " + name + " productionYear = " + productionYear);
        }
    }

    List<Employee> exercises(String query) throws SQLException {
        List<Employee> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection
                .prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            Employee employee = new Employee(id, name, salary);
            list.add(employee);
        }
        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        StatementExample statementExample = new StatementExample();
//        statementExample.createTable();
//        statementExample.fillTableWith5EmployeesUsingPreparedStatement();
//        statementExample.deleteFromTableEmployeesUsingPreparedStatement(4000);
//        statementExample.deleteTable("employee");
//        statementExample.tests();
//        statementExample.exercise13();
        String exercise13 = "SELECT * FROM employee";
        String exercise14 = "SELECT * FROM employee ORDER BY salary DESC";
        String exercise15 = "SELECT * FROM employee WHERE salary > 2000";
        String exercise16 = "SELECT * FROM employee WHERE name LIKE " + "'J%'";
        statementExample.exercises(exercise13).stream().forEach(System.out::println);

    }

}
