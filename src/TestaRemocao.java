import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM produto WHERE ID > 2");
        Integer modifiedRows = statement.getUpdateCount();
        System.out.println("Modified rows:\t" + modifiedRows);
    }
}
