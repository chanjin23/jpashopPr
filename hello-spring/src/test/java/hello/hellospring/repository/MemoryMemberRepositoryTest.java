package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach  //메서드가 끝날 때마다 실행되는 노테이션
    public void afterEach(){
        repository.clearStore();
    }
    //테스트는 순서와 상관없이 동작해야 한다. 즉 순서에 의존받으면 안된다.

    @Test
    public void save(){
        Member member =new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //optional은 get메서드를 이용해서 꺼낼수 있다.
//       System.out.println("result = " + (result == member));    //결과 값과 member가 정확하면 true로 띄움
//       Assertions.assertEquals(member, result);    //값에 이상이 생기지 않으면 아무 창도 출력하지 않지만 문제가 생기면 오류를 일으킴.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);  //얘는 그냥 같은지를 확인하는 거여서 순서상관x
    }

    @Test
    public void findAll(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
