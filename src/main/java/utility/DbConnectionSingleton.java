package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnectionSingleton {

    private static DbConnectionSingleton instance;

    private final Connection connection;

    private static final String URL = "jdbc:postgresql://172.25.28.85:5433/agenda";

    private static final String PASSWORD = "pwdadmin";

    private static final String USER = "postgres";

    // costruttore privato nel singleton
    private DbConnectionSingleton() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgresSQL non trovato", e);
        }
    }

    public static DbConnectionSingleton getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DbConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}