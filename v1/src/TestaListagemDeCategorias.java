import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CategoriaDao;
import factory.ConnectionFactory;
import modelo.Categoria;
import modelo.Produto;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().connectToDatabase()) {
            CategoriaDao categoriaDAO = new CategoriaDao(connection);
            List<Categoria> listaDeCategorias = categoriaDAO.listarComProduto();
            listaDeCategorias.stream().forEach(ct -> {
                System.out.println(ct.getNome());
                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });
        }
    }
}
