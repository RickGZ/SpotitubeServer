package services.rest;

import datasource.IPlaylistsDAO;
import datasource.ITrackDAO;
import domain.Playlist;
import domain.Track;
import services.UserSingleton;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class Playlists implements IPlaylists {

    @Inject
    private IPlaylistsDAO playlistsDAO;

    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadPlaylists() {
        JsonObject playlists;

        playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject addPlaylist(Playlist playlist) {
        JsonObject playlists;

        playlistsDAO.addPlaylist(playlist);

        playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }


    @GET @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksInPlaylist(@PathParam("id") int id) {
        JsonObject tracksObject;

        tracksObject = trackDAO.findTracksInPlaylist(id);

        return tracksObject;

    }

    @DELETE @Path("{pId}/tracks/{tId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject removeTrackFromPlaylist(@PathParam("pId") int playlistId, @PathParam("tId") int trackId) {
        JsonObject tracksObject;

        trackDAO.removeTrackFromPlaylist(trackId, playlistId);

        tracksObject = trackDAO.findTracksInPlaylist(playlistId);

        return tracksObject;
    }

    @POST @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject addTrackToPlaylist(@PathParam("id") int playlistId, Track track) {
        JsonObject tracksInPlaylist;

        trackDAO.addTrackToPlaylist(playlistId, track);

        tracksInPlaylist = trackDAO.findTracksInPlaylist(playlistId);

        return tracksInPlaylist;
    }

    @PUT @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject editPlaylist(@PathParam("id") int playlistId, Playlist playlist) {
        JsonObject playlists;

        playlistsDAO.editPlaylist(playlist);

        playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }

    @DELETE @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject deletePlaylist(@PathParam("id") int playlistId) {
        JsonObject playlists;

        playlistsDAO.deletePlaylist(playlistId);

        playlists = playlistsDAO.findAllPlaylists(UserSingleton.getUser());

        return playlists;
    }
}
