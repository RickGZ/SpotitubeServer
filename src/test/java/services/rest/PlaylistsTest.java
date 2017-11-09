package services.rest;

import datasource.IPlaylistsDAO;
import datasource.ITrackDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import services.UserSingleton;

import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistsTest {

    @Mock
    private IPlaylistsDAO playlistsDAO;

    @Mock
    private ITrackDAO trackDAO;

    @InjectMocks
    private IPlaylists playlists = new Playlists();

    @Test
    public void testWhetherLoadPlaylistsReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(playlistsDAO.findAllPlaylists(UserSingleton.getUser())).thenReturn(jsonObject);
        response = playlists.loadPlaylists();

        //assert
        verify(playlistsDAO).findAllPlaylists(UserSingleton.getUser());
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherAddPlaylistsReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(playlistsDAO.findAllPlaylists(UserSingleton.getUser())).thenReturn(jsonObject);
        response = playlists.addPlaylist(null);

        //assert
        verify(playlistsDAO).findAllPlaylists(UserSingleton.getUser());
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherLoadTracksInPlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(trackDAO.findTracksInPlaylist(0)).thenReturn(jsonObject);
        response = playlists.loadTracksInPlaylist(0);

        //assert
        verify(trackDAO).findTracksInPlaylist(0);
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherRemoveTrackFromPlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(trackDAO.findTracksInPlaylist(0)).thenReturn(jsonObject);
        response = playlists.removeTrackFromPlaylist(0, 0);

        //assert
        verify(trackDAO).findTracksInPlaylist(0);
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherAddTrackToPlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(trackDAO.findTracksInPlaylist(0)).thenReturn(jsonObject);
        response = playlists.addTrackToPlaylist(0, null);

        //assert
        verify(trackDAO).findTracksInPlaylist(0);
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherEditPlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(playlistsDAO.findAllPlaylists(UserSingleton.getUser())).thenReturn(jsonObject);
        response = playlists.editPlaylist(0, null);

        //assert
        verify(playlistsDAO).findAllPlaylists(UserSingleton.getUser());
        assertEquals(jsonObject, response);
    }

    @Test
    public void testWhetherDeletePlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(playlistsDAO.findAllPlaylists(UserSingleton.getUser())).thenReturn(jsonObject);
        response = playlists.deletePlaylist(0);

        //assert
        verify(playlistsDAO).findAllPlaylists(UserSingleton.getUser());
        assertEquals(jsonObject, response);
    }

}
