package nl.ead.webservice.services.spotifyMatchingService;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.PlaylistRequest;
import com.wrapper.spotify.methods.UserPlaylistsRequest;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.SimplePlaylist;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpotifyConnector implements ISpotifyConnector {

    @Override
    public ArrayList<String> getPlaylistsIdByUserId(String userId) {
        final String clientId = "b3863f4672774a0a921f19d81d58a77f";
        final String clientSecret = "be215f004c7945e1b03789ed24541da1";
        final Api api = Api.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
        final ClientCredentialsGrantRequest request =
                api.clientCredentialsGrant().build();
        final SettableFuture<ClientCredentials> responseFuture =
                request.getAsync();
        Futures.addCallback(responseFuture,
                new FutureCallback<ClientCredentials>() {
            @Override
            public void onSuccess(ClientCredentials clientCredentials) {
                System.out.println("Successfully retrieved an access token! " +
                        clientCredentials.getAccessToken());
                System.out.println("The access token expires in " +
                        clientCredentials.getExpiresIn() + " seconds");
                api.setAccessToken(clientCredentials.getAccessToken());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Couldn't retriece access token");
            }
        });

        ArrayList<String> result = new ArrayList<>();
        final UserPlaylistsRequest request2 = api.getPlaylistsForUser(userId).build();
        try{
            final List<SimplePlaylist> playlists = request2.get().getItems();
            for(SimplePlaylist simplePlaylist : playlists){
                result.add(simplePlaylist.getId());
            }
        } catch (Exception e){
            System.out.println("Could not get playlists");
        }
        return result;
    }
}
