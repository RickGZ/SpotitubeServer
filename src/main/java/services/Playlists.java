package services;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class Playlists {

    @GET
    @Consumes(MediaType.APPLICATION_JSON);
    @Produces(MediaType.APPLICATION_JSON);
    public JsonObject loadPlaylists() {

    }
}
