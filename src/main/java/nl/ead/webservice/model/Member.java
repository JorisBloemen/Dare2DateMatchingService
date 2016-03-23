package nl.ead.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String spotifyId;
    private String youtubeId;

    public Member(String name, String spotifyId, String youtubeId) {
        this.name = name;
        this.spotifyId = spotifyId;
        this.youtubeId = youtubeId;
    }

    public Member() {
    }

    public Long getId() { return id; }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getName() {
        return name;
    }
}
