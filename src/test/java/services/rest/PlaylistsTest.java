package services.rest;

import datasource.IPlaylistsDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import javax.json.JsonObject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistsTest {

    @Mock
    private IPlaylistsDAO playlistsDAO;

    @InjectMocks
    private IPlaylists playlists = new Playlists();

    @Test
    public void testWhetherPlaylistsReturnsJsonObjectForPlaylists() {
        //arrange
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(playlists.loadPlaylists()).thenReturn(jsonObject);

        //assert
    }

}
