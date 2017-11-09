package datasource;

import org.junit.jupiter.api.Test;

public class PlaylistsDAOTest {

    @Test
    public void playlistsDAOImplementsInterface() {
        //arrange
        IPlaylistsDAO playlistsDAO;
        //act
        playlistsDAO = new PlaylistsDAO();
        //assert
        IPlaylistsDAO.class.isAssignableFrom(playlistsDAO.getClass());
    }
}
