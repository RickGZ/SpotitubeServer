package domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject attemptLogin() {
        System.out.println("hoi");

        JsonObject jo = Json.createObjectBuilder().add("token", "1234-1234-1234").add("user", "Rick Zweers").build();

        return jo;
    }

}