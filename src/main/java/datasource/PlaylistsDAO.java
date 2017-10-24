package datasource;

import domain.Playlist;
import services.UserSingleton;

import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistsDAO extends Database {

    //TODO: LANGE METHODE. opdelen in kleinere?
    public JsonObject findAllPlaylists(String user) {
        PreparedStatement statement;
        ResultSet result = null;
        boolean owner = false;

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
            while(result.next()){
                JsonArray emptyArray = Json.createArrayBuilder().build();
                if(result.getString("owner").equals(user)) {
                    owner = true;
                }
                System.out.println(result.getString("owner") + " - " + user);

                JsonObject playlist = Json.createObjectBuilder().add("id", result.getInt("id")).
                        add("name", result.getString("name")).add("owner", owner).
                        add("tracks", emptyArray).build();

                arrayBuilder.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonArray playlists = arrayBuilder.build();
        JsonObject playlistReturnable = Json.createObjectBuilder().add("playlists", playlists).
                add("length", getTotalLengthPlaylists()).build();

        return playlistReturnable;
    }

    public void addPlaylist(Playlist playlist) {
        PreparedStatement statement;

        String name = playlist.getName();
        String owner = UserSingleton.getUser();

        try {
            statement = connection.prepareStatement("INSERT INTO Playlist(name, owner) VALUES(?, ?)");
            statement.setString(1, name);
            statement.setString(2, owner);

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editPlaylist(Playlist playlist) {
        PreparedStatement statement;

        int playlistId = playlist.getId();
        String newName = playlist.getName();

        try{
            statement = connection.prepareStatement("UPDATE Playlist SET name = ? WHERE id = ?");
            statement.setString(1, newName);
            statement.setInt(2, playlistId);

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlaylist(int playlistId) {
        PreparedStatement statement;

        try{
            statement = connection.prepareStatement("DELETE FROM Playlist WHERE id = ?");
            statement.setInt(1, playlistId);

            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getTotalLengthPlaylists() {
        PreparedStatement statement;
        ResultSet result;
        int length = 0;

        try {
            statement = connection.prepareStatement("SELECT SUM(t.duration) AS 'duration' FROM TrackInPlaylist tip INNER JOIN Track t ON tip.trackId = t.id");
            System.out.println(statement);
            result = statement.executeQuery();
            result.next();
            length = result.getInt("duration");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return length;
    }
}
