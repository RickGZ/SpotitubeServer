package services;

import domain.Track;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/playlists")
public class Playlists {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadPlaylists() {

        System.out.println("playlists laden");


        JsonObject track = Json.createObjectBuilder().add("id", 2).add("title", "testtitel").
                add("performer", "testperformer").add("duration", 100).add("album", "testalbum").
                add("playcount", 0).add("publicationDate", 10000000).add("description", "kekkerino").
                add("offlineAvailable", true).build();

        JsonArray tracks = Json.createArrayBuilder().add(track).build();


        JsonObject playlist = Json.createObjectBuilder().add("id", 1).add("name", "test").
                add("owner", true).add("tracks", tracks).build();

        JsonArray playlistArray = Json.createArrayBuilder().add(playlist).build();

        JsonObject playlists = Json.createObjectBuilder().add("playlists", playlistArray).
                add("length", 100).build();


        return playlists;
    }


    @GET @Path("/1/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject loadTracksInPlaylist() {

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
