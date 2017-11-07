package datasource;

import org.junit.jupiter.api.Test;

public class UserDAOTest {

    @Test
    public void userDAOImplementsInterface() {
        //arrange
        IUserDAO userDAO;
        //act
        userDAO = new UserDAO();
        //assert
        IUserDAO.class.isAssignableFrom(userDAO.getClass());
    }
}
