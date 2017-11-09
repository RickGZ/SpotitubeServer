package datasource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import services.UserSingleton;

import javax.json.JsonArray;
import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistsDAOTest {

    @InjectMocks
    private IPlaylistsDAO playlistsDAO;

    @Test
    public void playlistsDAOImplementsInterface() {
        //arrange
        IPlaylistsDAO playlistsDAO;
        //act
        playlistsDAO = new PlaylistsDAO();
        //assert
        IPlaylistsDAO.class.isAssignableFrom(playlistsDAO.getClass());
    }+

//    @Test
//    public void testWhetherFindAllPlaylistsReturnsJsonObject() {
//        //arrange
//        JsonObject response;
//        JsonArray jsonArray = mock(JsonArray.class);
//        JsonObject jsonObject = mock(JsonObject.class);
//
//        //act
//        when(playlistsDAO.createPlaylistArray(UserSingleton.getUser(), null)).thenReturn(jsonArray);
//        response = playlistsDAO.findAllPlaylists(UserSingleton.getUser());
//
//        //assert
//        verify(playlistsDAO).findAllPlaylists(UserSingleton.getUser());
//        assertEquals(jsonObject, response);
//    }
}
