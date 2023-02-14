package org.agoncal.course.quarkus.jdbc;

import java.time.Instant;
import java.util.Objects;

public class Artist {

    private long id;
    private String name;
    private String bio;
    private Instant createdDated = Instant.now();

    public Artist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public Artist() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Instant getCreatedDated() {
        return createdDated;
    }

    public void setCreatedDated(Instant createdDated) {
        this.createdDated = createdDated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist artist)) return false;
        return getId() == artist.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", createdDated=" + createdDated +
                '}';
    }
}
