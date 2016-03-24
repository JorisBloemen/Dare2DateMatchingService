package nl.ead.webservice.services.spotifyMatchingService;

import nl.ead.webservice.services.IAPIMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SpotifyMatchingService implements IAPIMatchingService{

    @Autowired
    private ISpotifyConnector spotifyConnector;

    @Override
    public HashMap<String, Number> calculateMatches(
            String id, ArrayList<String> possibleMatches) {
        HashMap<String, Number> result = new HashMap<>();
        result.put("32154894", 5);
        result.put("63254122", 2);
        result.put("78541269", 6);
        result.put("11253652", 3);
        return result;
    }
}
