package services.rest;

import datasource.ITrackDAO;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tracks")
public class Tracks implements ITracks {

    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId) {
        JsonObject tracks;

        tracks = trackDAO.findAllTracksNotInPlaylist(playlistId);

        return tracks;
    }
}
