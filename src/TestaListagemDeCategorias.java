import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoriaDao;
import modelo.Categoria;

public class TestaListagemDeCategorias {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().connectToDatabase()) {
            CategoriaDao categoriaDao = new CategoriaDao(connection);
            List<Categoria> categorias = categoriaDao.listar();
            categorias.stream().forEach(each_categoria -> System.out.println(each_categoria.toString()));
        }
    }
}
