package jpabookPr.jpashopPr.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jpabookPr.jpashopPr.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //어노테이션안에 Component가 있기 때문에 스프링부트에서 자동으로 스프링 빈등록
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext //JPA 엔티티 매니저에 자동으로 주입을 시켜준다. //spring에서 JPA persistence를 안해도 자동으로 주입을 시켜준다.
//    @Autowired  //원래는 Autowired를 하면 안되는데 지원을 해준다.
    private final EntityManager em;   //injection을 시켜준다.

    /*@PersistenceUnit
    private EntityManagerFactory emf;*/ //만약 직접 엔티티매니저를 꺼내고싶다면 다음과 같이 해서 주입받으면 됨.

    public void save(Member member) {
        em.persist(member); //영속성 컨텍스트에 member를 넣는다.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);   //JPA find를 이용해서 단일 조회를 한다.
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();   //jpql sql은 쿼리를 대상으로 하지만 jpql은 entity기준으로 한다.
        //JPQL을 이용하여 List 값을 얻어온다.
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        //JPQL을 이용하여 List 값을 얻어온다.
    }

}
