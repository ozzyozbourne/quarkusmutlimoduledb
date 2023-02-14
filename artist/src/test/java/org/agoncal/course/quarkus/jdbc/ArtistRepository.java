package org.agoncal.course.quarkus.jdbc;

import io.quarkus.logging.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

    @Inject
    DataSource dataSource;

    public void persist(Artist artist) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "INSERT INTO t_artists (id, name, bio, created_date) VALUES (?, ?, ?, ?)";
        artist.setId(Math.abs(new Random().nextLong()));
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, artist.getId());
            ps.setString(2, artist.getName());
            ps.setString(3, artist.getBio());
            ps.setTimestamp(4, Timestamp.from(artist.getCreatedDated()));
            Log.info("ROWS AFFECTED -> " + ps.executeUpdate());
        }conn.close();
    }

    public Artist findById(long id) throws SQLException {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, bio, created_date FROM t_artists WHERE id = ?";
        Artist artist = new Artist();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()) {
                artist.setId(resultSet.getLong(1));
                artist.setName(resultSet.getString(2));
                artist.setBio(resultSet.getString(3));
                artist.setCreatedDated(resultSet.getTimestamp(4).toInstant());
            }}
        conn.close();
        return artist;
    }
}
