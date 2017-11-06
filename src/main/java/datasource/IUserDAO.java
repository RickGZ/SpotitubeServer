package datasource;

public interface IUserDAO {
    boolean findUserByUsernameAndPassword(String user, String password);
}
