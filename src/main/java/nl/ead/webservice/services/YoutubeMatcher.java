package nl.ead.webservice.services;

import java.util.ArrayList;
import java.util.HashMap;

public class YoutubeMatcher implements IMatcher{

    private String memberToMatchId;
    private ArrayList<String> possibleMatches;

    public YoutubeMatcher(String id, ArrayList<String> possibleMatches){
        this.memberToMatchId = id;
        this.possibleMatches = possibleMatches;
    }
    @Override
    public HashMap<String, Number> getMatchPoints() {
        return null;
    }
}
