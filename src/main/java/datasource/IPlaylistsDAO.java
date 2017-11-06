package datasource;

import domain.Playlist;

import javax.json.JsonObject;

public interface IPlaylistsDAO {
    JsonObject findAllPlaylists(String user);

    void addPlaylist(Playlist playlist);

    void editPlaylist(Playlist playlist);

    void deletePlaylist(int playlistId);

    int getTotalLengthPlaylists();
}
