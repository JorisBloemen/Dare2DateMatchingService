package nl.ead.webservice.dao;

import com.sun.corba.se.impl.encoding.EncapsInputStream;
import nl.ead.webservice.model.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberDao implements IMemberDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Member getMember(long id){
        return em.find(Member.class, id);
    }
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
