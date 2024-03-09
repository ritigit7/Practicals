package AD_Practicals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    private static final String DB_URL = "jdbc:mysql://localhost/bookstore";
    private static final String USER = "root";
    private static final String PASS = "ritiksql";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            createBooksTable(connection);
            insertRecords(connection);
            searchAndUpdatePrice(connection, "The Catcher in the Rye", 15.99f);
            deleteRecord(connection, "The Catcher in the Rye");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createBooksTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Books (" +
                "BookTitle VARCHAR(50) NOT NULL," +
                "Authorname VARCHAR(50) NOT NULL," +
                "Publisher VARCHAR(50) NOT NULL," +
                "Price DECIMAL(5, 2) NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void insertRecords(Connection connection) throws SQLException {
        String sql = "INSERT INTO Books (BookTitle, Authorname, Publisher, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 20; i++) {
                preparedStatement.setString(1, "Book " + i);
                preparedStatement.setString(2, "Author " + i);
                preparedStatement.setString(3, "Publisher " + i);
                preparedStatement.setFloat(4, 9.99f);
                preparedStatement.executeUpdate();
            }
        }
    }

    private static void searchAndUpdatePrice(Connection connection, String title, float newPrice) throws SQLException {
        String sql = "UPDATE Books SET Price = ? WHERE BookTitle = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setFloat(1, newPrice);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        }
    }

    private static void deleteRecord(Connection connection, String title) throws SQLException {
        String sql = "DELETE FROM Books WHERE BookTitle = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        }
    }
}
