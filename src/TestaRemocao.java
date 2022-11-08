import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE ID > ?");
        preparedStatement.setInt(1, 2);
        preparedStatement.execute();
        Integer modifiedRows = preparedStatement.getUpdateCount();
        System.out.println("Modified rows:\t" + modifiedRows);
    }
}
