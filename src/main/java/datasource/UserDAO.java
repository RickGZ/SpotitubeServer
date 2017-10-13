package datasource;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends Database {

    public boolean findUserByUsernameAndPassword(String username, String password) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement("SELECT * from User WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            result = statement.executeQuery();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(result.getString("username").equals(username)) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
