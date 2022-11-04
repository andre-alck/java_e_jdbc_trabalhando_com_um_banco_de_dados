import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual", "root", "root");
    }
}
