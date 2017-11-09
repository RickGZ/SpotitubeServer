package services.rest;

import datasource.IUserDAO;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.json.Json;
import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private Login login;

    @Test
    public void testWhetherAttemptLoginReturnsCorrectJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject;

        User user = new User();

        user.setUser("rick");
        user.setPassword("test");

        jsonObject = Json.createObjectBuilder().add("token", "1234-1234-1234").
                add("user", user.getUser()).build();

        //act
        when(userDAO.findUserByUsernameAndPassword(user.getUser(), user.getPassword())).thenReturn(true);
        response = login.attemptLogin(user);

        //assert
        verify(userDAO).findUserByUsernameAndPassword(user.getUser(), user.getPassword());
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherFailedAttemptLoginReturnsNull() {
        //arrange
        JsonObject response;
        JsonObject jsonObject;

        User user = new User();

        user.setUser("rick");
        user.setPassword("test");

        jsonObject = null;

        //act
        when(userDAO.findUserByUsernameAndPassword(user.getUser(), user.getPassword())).thenReturn(false);
        response = login.attemptLogin(user);

        //assert
        verify(userDAO).findUserByUsernameAndPassword(user.getUser(), user.getPassword());
        assertEquals(jsonObject, response);
    }
}
