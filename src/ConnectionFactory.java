import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual");

        comboPooledDataSource.setMaxPoolSize(5);

        this.dataSource = comboPooledDataSource;
    }

    public Connection connectToDatabase() throws SQLException {
        return this.dataSource.getConnection();
    }
}
