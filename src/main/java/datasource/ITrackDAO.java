package datasource;

import domain.Track;

import javax.json.JsonObject;

public interface ITrackDAO {
    JsonObject findTracksInPlaylist(int playlistId);

    void removeTrackFromPlaylist(int trackId, int playlistId);

    JsonObject findAllTracksNotInPlaylist(int playlistId);

    void addTrackToPlaylist(int playlistId, Track track);
}
