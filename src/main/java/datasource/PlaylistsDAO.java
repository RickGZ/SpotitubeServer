package datasource;

import domain.User;

import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistsDAO extends Database {

    public JsonObject findAllPlaylists(User user) {
        PreparedStatement statement = null;
        ResultSet result = null;

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            statement = connection.prepareStatement("SELECT * from Playlist");

            System.out.println(statement);
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            result.beforeFirst();

            if(!result.next()) {
                System.out.println("No records found!");
            }
            else {
                do{
                    boolean owner;
                    JsonArray emptyArray = Json.createArrayBuilder().build();

                    if(result.getString("owner") == user.getUser()) {
                        owner = true;
                    }
                    else {
                        owner = false;
                    }

                    JsonObject playlist = Json.createObjectBuilder().add("id", result.getInt("id")).
                            add("name", result.getString("name")).add("owner", owner).
                            add("tracks", emptyArray).build();

                    arrayBuilder.add(playlist);

                } while(result.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray playlists = arrayBuilder.build();

        JsonObject playlistReturnable = Json.createObjectBuilder().add("playlists", playlists).build();

        return playlistReturnable;


    }
}
