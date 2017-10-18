package datasource;

import domain.User;

import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistsDAO extends Database {

    //TODO: LANGE METHODE. opdelen in kleinere?
    public JsonObject findAllPlaylists(String user) {
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

                    if(result.getString("owner").equals(user)) {
                        owner = true;
                    }
                    else {
                        owner = false;
                    }

                    System.out.println(result.getString("owner") + " - " + user);

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

        JsonObject playlistReturnable = Json.createObjectBuilder().add("playlists", playlists).
                add("length", 100).build();

        return playlistReturnable;


    }
}
