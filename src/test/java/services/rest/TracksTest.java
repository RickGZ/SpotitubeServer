package services.rest;

import datasource.ITrackDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TracksTest {

    @Mock
    private ITrackDAO trackDAO;

    @InjectMocks
    private ITracks tracks = new Tracks();

    @Test
    public void testWhetherLoadTracksNotInPlaylistReturnsJsonObject() {
        //arrange
        JsonObject response;
        JsonObject jsonObject = mock(JsonObject.class);

        //act
        when(trackDAO.findAllTracksNotInPlaylist(0)).thenReturn(jsonObject);
        response = tracks.loadTracksNotInPlaylist(0);

        //assert
        verify(trackDAO).findAllTracksNotInPlaylist(0);
        assertEquals(jsonObject, response);
    }
}
