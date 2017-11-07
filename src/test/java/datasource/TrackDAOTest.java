package datasource;

import org.junit.jupiter.api.Test;

public class TrackDAOTest {

    @Test
    public void trackDAOImplementsInterface() {
        //arrange
        ITrackDAO trackDAO;
        //act
        trackDAO = new TrackDAO();
        //assert
        ITrackDAO.class.isAssignableFrom(trackDAO.getClass());
    }
}
