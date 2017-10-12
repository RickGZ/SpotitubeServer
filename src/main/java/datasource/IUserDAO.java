package datasource;

import domain.User;

public interface IUserDAO {
    User findUserByUsernameAndPassword(String username, String password);
}
