package nl.ead.webservice.services.youtubeMatchingService;

import java.util.ArrayList;

public interface IYoutubeConnector {
    ArrayList<String> getFavoritesIdsByUserId(String userId);
}
