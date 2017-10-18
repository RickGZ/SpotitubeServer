package datasource;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO extends Database {


    //TODO Wederom extreem lange methode. Móet verkort worden zodra het werkt.
    public JsonObject findTracksInPlaylist(int playlistId) {
        PreparedStatement statement = null;
        ResultSet resultTrackIds = null;
        ResultSet resultTracks = null;

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            statement = connection.prepareStatement("SELECT * from TracksInPlaylist WHERE playlistId = ?");
            statement.setInt(1, playlistId);

            System.out.println(statement);
            resultTrackIds = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement = connection.prepareStatement("SELECT * from Tracks");    //Laadt zo alle tracks. is dat nodig?

            System.out.println(statement);
            resultTracks = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultTracks.beforeFirst();

            do {
                //TODO
            } while(resultTracks.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
