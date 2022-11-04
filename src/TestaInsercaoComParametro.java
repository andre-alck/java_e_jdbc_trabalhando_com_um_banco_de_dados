import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.connectToDatabase();

        String nome = "MOUSE";
        String descricao = "MOUSE SEM FIO";

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?);",
                        Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, descricao);

        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            System.out.println("Generated id:\t" + id);
        }
    }
}