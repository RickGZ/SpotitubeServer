package datasource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    protected Connection connection;

    public Database() {
        retrieveProperties();

        tryLoadJdbcDriver();

        //connection = getConnection();
    }

    public String driver() {
        return properties.getProperty("driver");
    }

    public String connectionString() {
        return properties.getProperty("connectionString");
    }

    private void retrieveProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    protected Connection getConnection() {
        String connectionString = connectionString();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to database", e);
        }
        
        return connection;
    }

    private void tryLoadJdbcDriver() {
        try {
            Class.forName(driver());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Can't load JDBC Driver " + driver(), e);
        }
    }
}
