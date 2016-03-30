package nl.ead.webservice.services.youtubeMatchingService;

import nl.ead.webservice.services.IAPIMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class YoutubeMatchingService implements IAPIMatchingService {

    @Autowired
    private IYoutubeConnector youtubeConnector;

    public YoutubeMatchingService(){}

    public YoutubeMatchingService(IYoutubeConnector youtubeConnector){
        this.youtubeConnector = youtubeConnector;
    }

    @Override
    public HashMap<String, Number> calculateMatches(
            String id, ArrayList<String> possibleMatches) {
        HashMap<String, Number> result = new HashMap<>();
        ArrayList<String> baseFavoritesIds =
                this.youtubeConnector.getFavoritesTitlesByUserId(id);
        for(String possibleMatch : possibleMatches){
            int count = 0;
            ArrayList<String> favorites =
                    this.youtubeConnector.getFavoritesTitlesByUserId(
                            possibleMatch);
            for(String baseFavoriteId : baseFavoritesIds){
                for(String favoriteId : favorites){
                    if(baseFavoriteId.equals(favoriteId)){
                        count++;
                    }
                }
            }
            result.put(possibleMatch, count);
        }
        return result;
    }
}

