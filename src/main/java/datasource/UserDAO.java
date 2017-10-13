package datasource;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends Database {

    public boolean findUserByUsernameAndPassword(String user, String password) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement("SELECT * from User WHERE username = ? AND password = ?");
            statement.setString(1, user);
            statement.setString(2, password);

//            statement.close();
//            connection.close();
            System.out.println(statement);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            result.beforeFirst();
            result.next();
            if(result.getString("username").equals(user)) {
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
