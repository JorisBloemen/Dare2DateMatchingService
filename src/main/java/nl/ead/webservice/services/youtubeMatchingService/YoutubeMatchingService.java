package nl.ead.webservice.services.youtubeMatchingService;

import nl.ead.webservice.services.IAPIMatchingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class YoutubeMatchingService implements IAPIMatchingService {

    @Override
    public HashMap<String, Number> calculateMatches(String id, ArrayList<String> possibleMatches) {
        return null;
    }
}
