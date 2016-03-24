package nl.ead.webservice.services;

import nl.ead.webservice.ResultList;
import nl.ead.webservice.dao.IMemberDao;
import nl.ead.webservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MatchService implements IMatchService{

    @Autowired
    private IAPIMatchingService spotifyMatchingService;

    @Autowired
    private IMemberDao memberDoa;

    @Override
    public ResultList getMatches(Long id) {
        Member member = this.memberDoa.getMember(id);
        List<Member> otherMembers = this.memberDoa.getOtherMembers(id);
        HashMap<String, Number> spotifyMatchResults = this.spotifyMatchingService.calculateMatches(member.getSpotifyId());

        // TODO: Vullen resultlist
        return new ResultList();
    }
}
