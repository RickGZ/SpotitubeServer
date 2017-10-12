package datasource;

public interface IUserDAO {
    boolean findUserByUsernameAndPassword(String username, String password);
}
