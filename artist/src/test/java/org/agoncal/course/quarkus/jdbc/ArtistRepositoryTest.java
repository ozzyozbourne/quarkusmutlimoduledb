package org.agoncal.course.quarkus.jdbc;

import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    public void shouldCreateAndFindAnArtist() throws SQLException {
        Log.info("Going to run the tests");
        Artist artist = new Artist("name", "bio");
        repository.persist(artist);
        assertNotNull(artist.getId());
        Log.info(artist.toString());
        artist = repository.findById(artist.getId());
        assertEquals("name", artist.getName());
        Log.info(artist.toString());
        Log.info("SUCCESS quarkus is awesome");
    }
}
