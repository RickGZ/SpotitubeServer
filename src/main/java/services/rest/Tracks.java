package services.rest;

import datasource.TrackDAO;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tracks")
public class Tracks {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId) {
        System.out.println("tracks nog niet in betreffende playlist laden");

        TrackDAO trackDAO = new TrackDAO();

        JsonObject tracks = trackDAO.findAllTracksNotInPlaylist(playlistId);

        return tracks;
    }
}
