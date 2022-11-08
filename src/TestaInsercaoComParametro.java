import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.ConnectionFactory;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.connectToDatabase()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?);",
                            Statement.RETURN_GENERATED_KEYS);) {

                addProdutoToDatabase("MOUSE", "MOUSE SEM FIO", preparedStatement);
                addProdutoToDatabase("SMART TV", "45 POLEGADAS", preparedStatement);
                addProdutoToDatabase("RÁDIO", "BATERIA", preparedStatement);

                connection.commit();
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("Rollback executado.");
                connection.rollback();
            }
        }

    }

    private static void addProdutoToDatabase(String nome, String descricao, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);

        if (nome.equals("RÁDIO")) {
            throw new RuntimeException("Não é possível adicionar produto = RÁDIO.");
        }

        preparedStatement.execute();

        try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                System.out.println("Generated id:\t" + id);
            }

        }
    }
}