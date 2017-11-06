package services.rest;

import datasource.IUserDAO;
import datasource.UserDAO;
import domain.User;
import services.UserSingleton;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login implements ILogin {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject attemptLogin(User user) {
        UserSingleton.setUser(user.getUser());

        IUserDAO userDAO = new UserDAO();

        if(userDAO.findUserByUsernameAndPassword(user.getUser(), user.getPassword())) {
            JsonObject jo = Json.createObjectBuilder().add("token", "1234-1234-1234").add("user", user.getUser()).build();

            return jo;
        }
        return null;
    }

}