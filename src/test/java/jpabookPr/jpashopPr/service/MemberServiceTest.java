package jpabookPr.jpashopPr.service;

import jakarta.persistence.EntityManager;
import jpabookPr.jpashopPr.domain.Member;
import jpabookPr.jpashopPr.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)    //spring을 사용하기 위해선 SpringRunner가 필요하다.
@SpringBootTest //spring부트를 띄우기위해선 필요하다.
@Transactional  //테스트에서 트랜잭션을 하면 롤백해버린다.
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    /*@Autowired
    EntityManager em;*/

    @Test
//    @Rollback(false)    //롤백을 false를 하면 insert문이 나가는 것을 볼 수 있다.
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); //만약 insert문을 보고싶다면 flush를 작성하면된다. flush -> DB에 쿼리가 강제로 나간다.
        assertThat(member).isEqualTo(memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)   //try-catch문을 작성하지 않아도 예외를 처리해준다.
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);    //예외발생

        //then
        fail("예외 발생");
    }


}