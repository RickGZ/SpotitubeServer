package services;

import datasource.PlaylistsDAO;
import datasource.TrackDAO;
import domain.Playlist;
import domain.User;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
