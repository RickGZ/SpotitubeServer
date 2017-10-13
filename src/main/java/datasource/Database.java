package datasource;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public Database() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }
}
