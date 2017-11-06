package services.rest;

import domain.Playlist;
import domain.Track;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface IPlaylists {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject loadPlaylists();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject addPlaylist(Playlist playlist);

    @GET @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject loadTracksInPlaylist(@PathParam("id") int id);

    @DELETE @Path("{pId}/tracks/{tId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject removeTrackFromPlaylist(@PathParam("pId") int playlistId, @PathParam("tId") int trackId);

    @POST @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject addTrackToPlaylist(@PathParam("id") int playlistId, Track track);

    @PUT @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject editPlaylist(@PathParam("id") int playlistId, Playlist playlist);

    @DELETE @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject deletePlaylist(@PathParam("id") int playlistId);
}
