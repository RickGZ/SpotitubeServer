package services;

import datasource.PlaylistsDAO;
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

//        JsonObject track = Json.createObjectBuilder().add("id", 2).add("title", "testtitel").
//                add("performer", "testperformer").add("duration", 100).add("album", "testalbum").
//                add("playcount", 0).add("publicationDate", 10000000).add("description", "kekkerino").
//                add("offlineAvailable", true).build();
//
//        JsonArray tracks = Json.createArrayBuilder().add(track).build();
//
//
//        JsonObject playlist = Json.createObjectBuilder().add("id", 1).add("name", "test").
//                add("owner", true).add("tracks", tracks).build();
//
//        JsonArray playlistArray = Json.createArrayBuilder().add(playlist).build();
//
//        JsonObject playlists = Json.createObjectBuilder().add("playlists", playlistArray).
//                add("length", 100).build();
//
//
//        return playlists;

        return playlists;
    }


    @GET @Path("{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksInPlaylist(@PathParam("id") int id) {

        System.out.println("loading tracks in playlist");

        JsonObject track = Json.createObjectBuilder().add("id", 1).add("title", "testtitel").
                add("performer", "testperformer").add("duration", 100).add("album", "testalbum").
                add("playcount", 0).add("publicationDate", 10000000).add("description", "kekkerino").
                add("offlineAvailable", true).build();

        JsonArray tracks = Json.createArrayBuilder().add(track).build();

        JsonObject tracksObject = Json.createObjectBuilder().add("tracks", tracks).build();

        return tracksObject;

    }
}
