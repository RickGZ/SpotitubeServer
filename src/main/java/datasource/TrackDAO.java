package datasource;

import domain.Track;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO extends Database {

    public JsonObject findTracksInPlaylist(int playlistId) {
        PreparedStatement statement;
        ResultSet resultTracks = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM Track t INNER JOIN TrackInPlaylist tp ON t.id = tp.trackId WHERE tp.playlistId = ?");
            statement.setInt(1, playlistId);

            System.out.println(statement);
            resultTracks = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray tracks = createTrackArray(resultTracks);
        JsonObject returnable = Json.createObjectBuilder().add("tracks", tracks).build();
        return returnable;
    }

    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("DELETE FROM TrackInPlaylist WHERE trackId = ? AND playlistId = ?");
            statement.setInt(1, trackId);
            statement.setInt(2, playlistId);

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete failed.");
        }
    }

    public JsonObject findAllTracksNotInPlaylist(int playlistId) {
        PreparedStatement statement;
        ResultSet result = null;
        JsonArray trackArray;

        try {
            statement = connection.prepareStatement("SELECT * FROM Track t WHERE id NOT IN (SELECT trackId FROM TrackInPlaylist WHERE playlistId = ?)");
            statement.setInt(1, playlistId);

            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        trackArray = createTrackArray(result);
        JsonObject returnable = Json.createObjectBuilder().add("tracks", trackArray).build();
        return returnable;
    }

    private JsonArray createTrackArray(ResultSet result) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        boolean offlineAvailable;
        try{
            result.beforeFirst();
            while(result.next()) {
                try{
                    offlineAvailable = result.getBoolean("offlineAvailable");
                } catch (SQLException e){
                   offlineAvailable = false;
                }
                JsonObject track = Json.createObjectBuilder().
                        add("id", result.getInt("id")).
                        add("title", result.getString("title")).
                        add("performer", result.getString("performer")).
                        add("duration", result.getInt("duration")).
                        add("album", result.getString("album")).
                        add("playcount", result.getInt("playcount")).
                        add("publicationDate", result.getString("publicationDate")).
                        add("description", result.getString("description")).
                        add("offlineAvailable", offlineAvailable).
                        build();

                arrayBuilder.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray trackArray = arrayBuilder.build();
        return trackArray;
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        PreparedStatement statement;
        int trackId = track.getId();
        boolean offlineAvailable = track.isOfflineAvailable();

        try {
            statement = connection.prepareStatement("INSERT INTO TrackInPlaylist VALUES(?, ?, ?)");
            statement.setInt(1, trackId);
            statement.setInt(2, playlistId);
            statement.setBoolean(3, offlineAvailable);

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
