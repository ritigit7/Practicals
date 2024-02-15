import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/temp";
                String user = "root";
                String password = "ritiksql";

                try {
                        Connection connection = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected to the database");
                        Statement statement;
                        statement = connection.createStatement();
                        ResultSet resultSet;
                        resultSet = statement.executeQuery("select * from T1");
                        int enrol;
                        String name;
                        while (resultSet.next()) {
                                enrol = resultSet.getInt("enrol");
                                name = resultSet.getString("name").trim();
                                System.out.println("enrol : " + enrol
                                                + " name : " + name);
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