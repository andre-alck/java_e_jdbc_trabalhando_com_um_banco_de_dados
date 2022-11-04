import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja_virtual", "root",
                    "root");

            System.out.println("Conectado.");

            connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Erro: " + sqlException);
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Erro: " + classNotFoundException);
        }
    }
}