package nl.ead.webservice.services.youtubeMatchingService;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.*;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.model.*;
import org.springframework.stereotype.Service;
import com.google.api.services.youtube.*;
import com.google.api.*;

import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

@Service
public class YoutubeConnector implements IYoutubeConnector {
    final private String apiKey = "AIzaSyDYI-Pta3j3ndk9XSfmtglRwUYiGD2JEMI";

    final YouTube youtube = new YouTube.Builder(
            new NetHttpTransport(),
    new JacksonFactory(),
    new HttpRequestInitializer() {
        public void initialize(
                HttpRequest request) throws IOException {
        }
    }
    ).setApplicationName("youtube-matcher")
    .build();

    @Override
    public ArrayList<String> getFavoritesIdsByUserId(String userId) {
        ArrayList<String> result = new ArrayList<>();
        String favoritesId = getPlaylistId(userId, "Favorites");
        System.out.println(favoritesId);

        try {
            List<PlaylistItem> playlistItemList = new ArrayList<>();

            YouTube.PlaylistItems.List favorites = youtube.playlistItems()
                    .list("id,snippet");
            favorites.setKey(apiKey);
            favorites.setPlaylistId(favoritesId);
            favorites.setMaxResults(new Long(50));

            String nextToken = "";

            // Call the API one or more times to retrieve all items in the
            // list. As long as the API response returns a nextPageToken,
            // there are still more items to retrieve.
            do {
                favorites.setPageToken(nextToken);
                PlaylistItemListResponse playlistItemResult = favorites.execute();

                playlistItemList.addAll(playlistItemResult.getItems());

                nextToken = playlistItemResult.getNextPageToken();
            } while (nextToken != null);

            if (playlistItemList != null) {
                for(PlaylistItem playlistItem : playlistItemList){
                    System.out.println(playlistItem.getId());
                    result.add(playlistItem.getId());
                }
            } else {
                System.out.println("There were no results for your request.");
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return result;
    }

    private String getPlaylistId(String userId, String playListName) {
        try {
            YouTube.Playlists.List playlists = youtube.playlists()
                    .list("id,snippet");
            playlists.setKey(apiKey);
            playlists.setChannelId(userId);

            PlaylistListResponse playlistResponse = playlists.execute();

            List<Playlist> playlistResultList = playlistResponse.getItems();

            if (playlistResultList != null) {
                for(Playlist playlist : playlistResultList){
                    if(playlist.getSnippet().getTitle()
                            .equals(playListName)){
                        return playlist.getId();
                    }
                }
            } else {
                System.out.println("There were no results for your request.");
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return "";
    }
}

