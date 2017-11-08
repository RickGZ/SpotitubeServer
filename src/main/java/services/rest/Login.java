package services.rest;

import datasource.IUserDAO;
import domain.User;
import services.UserSingleton;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login implements ILogin {

    @Inject
    private IUserDAO userDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject attemptLogin(User user) {
        JsonObject jo = null;

        UserSingleton.setUser(user.getUser());

        if(userDAO.findUserByUsernameAndPassword(user.getUser(), user.getPassword())) {
            jo = Json.createObjectBuilder().add("token", "1234-1234-1234").add("user", user.getUser()).build();
        }
        return jo;
    }

}