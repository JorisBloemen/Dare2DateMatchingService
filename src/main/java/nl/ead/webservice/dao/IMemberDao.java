package nl.ead.webservice.dao;

import nl.ead.webservice.model.Member;

import java.util.List;

public interface IMemberDao {
    Member getMember (long id);
    List<Member> getOtherMembers(long id);
}
