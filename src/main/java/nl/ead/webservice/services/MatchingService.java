package nl.ead.webservice.services;

import nl.ead.webservice.dao.IMemberDao;
import nl.ead.webservice.dao.MemberDao;
import nl.ead.webservice.model.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchingService {
    /**
     * lijst maken spotifyservice (eigen ID, spotifyID)
     * lijst maken youtubeservice (eigen ID, youtubeID)
     * aanmaken spotifyservice met lijst -> krijgt lijst met ID, spotifyCount
     * aanmaken youtubeservice met lijst -> krijgt lijst met ID, youtubeCount
     */

    private IMemberDao memberDao;
    private Member memberToMatch;
    private List<Member> possibleMatches;
    private IMatcher spotifyMatcher;
    private IMatcher youtubeMatcher;

    public MatchingService(){
        this.memberDao = new MemberDao();
    }

    private void loadData(Long id){
        this.memberToMatch = memberDao.getMember(id);
        this.possibleMatches = memberDao.getOtherMembers(id);
    }

    public HashMap<Long, Number> spotifyMatches(Long id){
        loadData(id);
        ArrayList<String> possibleSpotifyMatches = new ArrayList<>();
        for(Member member : this.possibleMatches){
            possibleSpotifyMatches.add(member.getSpotifyId());
        }
        this.spotifyMatcher = new SpotifyMatcher(this.memberToMatch.getSpotifyId(), possibleSpotifyMatches);
        HashMap<String, Number> matchPoints = this.spotifyMatcher.getMatchPoints();
        HashMap<Long, Number> result = new HashMap<>();
        for(String id3 : matchPoints.keySet()){
            Long id2 = null;
            for (Member member : this.possibleMatches){
                if(member.getSpotifyId().equals(id3)){
                    id2 = member.getId();
                }
            }
            result.put(id2, matchPoints.get(id3));
        }
        return result;
    }
}
