package datasource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends Database {


    //TODO Wederom extreem lange methode. Móet verkort worden zodra het werkt.
    public JsonObject findTracksInPlaylist(int playlistId) {
        PreparedStatement statement = null;
        ResultSet resultTrackIds = null;
        ResultSet resultTracks = null;

        List<Integer> trackIds= new ArrayList<Integer>();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            statement = connection.prepareStatement("SELECT * from TrackInPlaylist WHERE playlistId = ?");
            statement.setInt(1, playlistId);

            System.out.println(statement);
            resultTrackIds = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultTrackIds.beforeFirst();
            do {
                resultTrackIds.next();
                System.out.println(resultTrackIds.getInt("trackId"));
                trackIds.add(resultTrackIds.getInt("trackId"));
            } while(resultTrackIds.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /////////////////////////////////////////////////
        try {
            statement = connection.prepareStatement("SELECT * from Track");    //Laadt zo alle tracks. is dat nodig?

            System.out.println(statement);
            resultTracks = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultTracks.beforeFirst();
            do {
                resultTracks.next();
                for(Integer i : trackIds) {
                    System.out.println(i);
                    if(i.equals(resultTracks.getInt("id"))) {
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
                }
            } while(resultTracks.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray tracks = arrayBuilder.build();
        JsonObject returnable = Json.createObjectBuilder().add("tracks", tracks).build();
        return returnable;
    }
}
