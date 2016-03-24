package nl.ead.webservice.services.spotifyMatchingService;

import java.util.ArrayList;

public interface ISpotifyConnector {
    ArrayList<String> getPlaylistsIdByUserId(String userId);
}
