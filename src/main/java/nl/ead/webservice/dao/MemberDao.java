package nl.ead.webservice.dao;

import com.sun.corba.se.impl.encoding.EncapsInputStream;
import nl.ead.webservice.model.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDao implements IMemberDao{

    @PersistenceContext
    private EntityManagerFactory emf;

    @Transactional
    @Override
    public Member getMember(long id){
        return emf.createEntityManager().find(Member.class, id);
    }

    @Override
    public List<Member> getOtherMembers(long id){
        return emf.createEntityManager().createQuery(
                "select m from Member m where m.id is not :id")
                .setParameter("id", id)
                .getResultList();
    }

//    @Override
//    public List<Member> getOtherMembers(long id) {
//        EntityManager em = emf.createEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Member> cQuery = cb.createQuery(Member.class);
//        Root<Member> m = cQuery.from(Member.class);
//        cQuery.select(m);
//        cQuery.where(cb.notEqual(m.get("id"), id));
//        TypedQuery<Member> tQuery = em.createQuery(cQuery);
//        List<Member> resultList = tQuery.getResultList();
//        return resultList;
//    }
}

//*/
//@Repository
//public class CalculationDao implements ICalculationDao {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Transactional
//    @Override
//    public void save(Calculation calculation) {
//        em.persist(calculation);
//    }
//}
