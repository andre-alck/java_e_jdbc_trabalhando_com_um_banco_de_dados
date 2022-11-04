import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual", "root",
                    "root");

            System.out.println("Connected.");

            connection.close();
        } catch (SQLException sqlException) {
            System.out.println("Exception: " + sqlException);
        }
    }
}