package nl.ead.webservice.services;

import java.util.ArrayList;
import java.util.HashMap;

public class SpotifyMatcher implements IMatcher{

    private String memberToMatchId;
    private ArrayList<String> possibleMatches;

    public SpotifyMatcher(String id, ArrayList<String> possibleMatches){
        this.memberToMatchId = id;
        this.possibleMatches = possibleMatches;
    }
    @Override
    public HashMap<String, Number> getMatchPoints() {
        String spotifyId = "1156090368";
        Number count = 1;
        HashMap<String, Number> result = new HashMap<String, Number>();
        result.put(spotifyId, count);
        return result;
    }
}
