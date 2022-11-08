import java.sql.Connection;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class TestaConexao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();

        connection.close();
    }
}