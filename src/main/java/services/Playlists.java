package services;

import datasource.PlaylistsDAO;
import datasource.TrackDAO;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class Playlists {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadPlaylists() {
        System.out.println("playlists laden");

        PlaylistsDAO playlistsDAO = new PlaylistsDAO();

        JsonObject playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }


    @GET @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksInPlaylist(@PathParam("id") int id) {
        TrackDAO trackDAO = new TrackDAO();

        JsonObject tracksObject = trackDAO.findTracksInPlaylist(id);

        return tracksObject;

    }

    @DELETE @Path("{id}/tracks/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject removeTrackFromPlaylist(@PathParam("id") int playlistId, @PathParam("id") int trackId) {
        TrackDAO trackDAO = new TrackDAO();

        System.out.println("remove track aangeroepen");

        trackDAO.removeTrackFromPlaylist(trackId, playlistId);

        JsonObject tracksObject = trackDAO.findTracksInPlaylist(playlistId);

        return tracksObject;
    }
}
