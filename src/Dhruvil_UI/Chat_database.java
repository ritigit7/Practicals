package Dhruvil_UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Chat_database {
        public static void main(String[] args) {
                // insertData(7, "sdflksdf ", 0, 0, 1);
                // fetchData();
                System.out.println(lastOrder());

        }

        protected static int lastOrder() {
                String url = "jdbc:mysql://localhost:3306/java_chat";
                String user = "root";
                String password = "ritiksql";
                int id = 0;
                try {
                        Connection connection = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected to the database");
                        Statement statement;
                        statement = connection.createStatement();
                        ResultSet resultSet;
                        resultSet = statement.executeQuery("select MAX(id) AS max_id from records");
                        while (resultSet.next()) {
                                id = resultSet.getInt("max_id");
                        }
                        System.out.println("id:" + id);
                        resultSet.close();
                        statement.close();
                        connection.close();
                } catch (SQLException e) {
                        System.out.println("Connection failed");
                        e.printStackTrace();
                }
                return id;
        }

        protected static void insertData(int id, String messages,
                        int client_1, int client_2, int client_3) {
                String url = "jdbc:mysql://localhost:3306/java_chat";
                String user = "root";
                String password = "ritiksql";
                try {
                        Connection connection = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected to the database");
                        // Statement statement = connection.createStatement();

                        String insertQuery = "INSERT INTO records VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                                // Set parameter values
                                statement.setInt(1, id);
                                statement.setString(2, messages);
                                statement.setInt(3, client_1);
                                statement.setInt(4, client_2);
                                statement.setInt(5, client_3);

                                // Execute the insert query
                                int rowsAffected = statement.executeUpdate();
                                System.out.println(rowsAffected + " row(s) affected");
                                statement.close();
                                connection.close();
                        } catch (SQLException e) {
                                System.out.println("Connection failed");
                                e.printStackTrace();
                        }
                } catch (SQLException e) {
                        System.out.println("Connection failed");
                        e.printStackTrace();
                }

        }

        protected static void fetchData() {
                String url = "jdbc:mysql://localhost:3306/java_chat";
                String user = "root";
                String password = "ritiksql";
                try {
                        Connection connection = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected to the database");
                        Statement statement;
                        statement = connection.createStatement();
                        ResultSet resultSet;
                        resultSet = statement.executeQuery("select * from records");
                        int id;
                        String messages;
                        int client_1;
                        int client_2;
                        int client_3;
                        while (resultSet.next()) {
                                id = resultSet.getInt("id");
                                messages = resultSet.getString("messages").trim();
                                client_1 = resultSet.getInt("client_1");
                                client_2 = resultSet.getInt("client_2");
                                client_3 = resultSet.getInt("client_3");
                                System.out.println("id:" + id + " messages:" + messages + " client_1:" + client_1
                                                + " client_2:" + client_2 + " client_3:" + client_3);
                        }
                        resultSet.close();
                        statement.close();
                        connection.close();
                } catch (SQLException e) {
                        System.out.println("Connection failed");
                        e.printStackTrace();
                }
        }
}