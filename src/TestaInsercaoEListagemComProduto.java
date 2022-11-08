import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDao;
import factory.ConnectionFactory;
import modelo.Produto;

public class TestaInsercaoEListagemComProduto {
    public static void main(String[] args) throws SQLException {
        Produto comoda = new Produto("CÔMODA", "CÔMODA VERTICAL");
        Produto armario = new Produto("ARMÁRIO", "ARMÁRIO CASAL");

        try (Connection connection = new ConnectionFactory().connectToDatabase()) {
            ProdutoDao produtoDao = new ProdutoDao(connection);
            produtoDao.add(comoda);
            produtoDao.add(armario);

            List<Produto> produtos = produtoDao.list();
            produtos.stream().forEach(each_produto -> System.out.println(each_produto));
        }
    }

}
