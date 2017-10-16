package datasource;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistsDAO extends Database {

    public JsonObject findAllPlaylists() {
        PreparedStatement statement = null;
        ResultSet result = null;

        JsonArray playlistArray = Json.createArrayBuilder();

        try {
            statement = connection.prepareStatement("SELECT * from Playlist");

            System.out.println(statement);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            result.beforeFirst();
            result.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
