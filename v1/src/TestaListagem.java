import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao FROM produto;");
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("ID");
            String nome = resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");

            System.out.println(id + " " + nome + " " + descricao);
        }

        connection.close();
    }
}
