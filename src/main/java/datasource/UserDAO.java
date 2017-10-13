package datasource;


public class UserDAO extends Database implements IUserDAO {

    public boolean findUserByUsernameAndPassword(String username, String password) {

        return true;
    }
}
