package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Produto;

public class ProdutoDao {
    private Connection connection;

    public ProdutoDao(Connection connection) {
        this.connection = connection;
    }

    public void add(Produto produto) throws SQLException {
        String sql = "INSERT INTO produto(nome, descricao) VALUES(?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    produto.setId(id);

                    System.out.println(produto);
                }
            }
        }
    }

    public List<Produto> list() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT id, nome, descricao FROM produto";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String nome = resultSet.getString(2);
                    String descricao = resultSet.getString(3);

                    produtos.add(new Produto(id, nome, descricao));
                }
            }

            return produtos;
        }
    }
}
