package datasource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO extends Database {

    public JsonObject findTracksInPlaylist(int playlistId) {
        PreparedStatement statement;
        ResultSet resultTracks = null;

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            statement = connection.prepareStatement("SELECT * FROM Track t INNER JOIN TrackInPlaylist tp ON t.id = tp.trackId WHERE tp.playlistId = ?");
            statement.setInt(1, playlistId);

            System.out.println(statement);
            resultTracks = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultTracks.beforeFirst();
            while(resultTracks.next()) {
                JsonObject track = Json.createObjectBuilder().
                        add("id", resultTracks.getInt("id")).
                        add("title", resultTracks.getString("title")).
                        add("performer", resultTracks.getString("performer")).
                        add("duration", resultTracks.getInt("duration")).
                        add("album", resultTracks.getString("album")).
                        add("playcount", resultTracks.getInt("playcount")).
                        add("publicationDate", resultTracks.getString("publicationDate")).
                        add("description", resultTracks.getString("description")).
                        add("offlineAvailable", resultTracks.getBoolean("offlineAvailable")).
                        build();

                arrayBuilder.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray tracks = arrayBuilder.build();
        JsonObject returnable = Json.createObjectBuilder().add("tracks", tracks).build();
        return returnable;
    }

    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("DELETE FROM TrackInPlaylist WHERE trackId = ? AND playlistId = ?");
            statement.setInt(1, trackId);
            statement.setInt(2, trackId);

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Delete failed.");
        }
    }
}
