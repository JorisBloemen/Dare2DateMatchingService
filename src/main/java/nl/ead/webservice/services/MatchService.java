package nl.ead.webservice.services;

import nl.ead.webservice.ComparedMember;
import nl.ead.webservice.ResultList;
import nl.ead.webservice.dao.IMemberDao;
import nl.ead.webservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MatchService implements IMatchService{

    @Autowired
    private IAPIMatchingService spotifyMatchingService;

    @Autowired
    private IAPIMatchingService youtubeMatchingService;

    @Autowired
    private IMemberDao memberDoa;

    @Override
    public ResultList getMatches(Long id) {
        ResultList resultList = new ResultList();

        Member member = this.memberDoa.getMember(id);
        List<Member> otherMembers = this.memberDoa.getOtherMembers(id);

        ArrayList<String> possibleSpotifyMatches = new ArrayList<>();
        ArrayList<String> possibleYoutubeMatches = new ArrayList<>();
        for(Member otherMember : otherMembers){
            possibleSpotifyMatches.add(otherMember.getSpotifyId());
            possibleYoutubeMatches.add(otherMember.getYoutubeId());
        }

        HashMap<String, Number> spotifyMatchResults =
                this.spotifyMatchingService.calculateMatches(
                        member.getSpotifyId(), possibleSpotifyMatches);

        HashMap<String, Number> youtubeMatchResults =
                this.youtubeMatchingService.calculateMatches(
                        member.getYoutubeId(), possibleYoutubeMatches);

        for(int i = 0; i < otherMembers.size(); i++){
            ComparedMember cm = new ComparedMember();
            cm.setId(otherMembers.get(i).getId());
            cm.setSpotifyMatchCount(
                    spotifyMatchResults
                            .get(otherMembers
                                    .get(i)
                                    .getSpotifyId())
                            .intValue());
            cm.setYoutubeMatchCount(
                    youtubeMatchResults
                            .get(otherMembers
                                    .get(i)
                                    .getYoutubeId())
                            .intValue()
            );
            resultList.getComparedMember().add(cm);
        }
        return resultList;
    }
}
