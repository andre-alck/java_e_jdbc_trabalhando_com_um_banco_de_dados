import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();

        Statement statement = connection.createStatement();
        statement.execute("SELECT id, nome, descricao FROM produto;");
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("ID");
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");

            System.out.println(id + " " + nome + " " + descricao);
        }

        connection.close();
    }
}
