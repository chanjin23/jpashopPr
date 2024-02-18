package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository =new MemoryMemberRepository();
        memberService =new MemberService(memberRepository);
    }

    @AfterEach
    public void AfterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 준비
        Member member1 = new Member();
        member1.setName("hello");

        //when 실행
        Long saveId = memberService.join(member1);

        //then 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());
    }
    //이렇게 구문을 짜면 너무 단순하다
    //테스트 코드를 짜는 이유는 예외 flow를 대비해서 만드는 것이다. 우리는 중복회원에 대해 예외처리를 해야되기 때문에
    //예외처리 test코드를 따로 짜야한다.

    @Test
    public void 중복_회원_예외(){
        //given 준비
        Member member1 =new Member();
        member1.setName("hello");
        Member member2 =new Member();
        member2.setName("hello");

        //when 실행
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //람다식이 예외가 발생하면 왼쪽에 있는 Exception이 발생해야한다.
        //만약 발생하지않는다면 예외 발생
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        //assertThrows를 이용해서 예외 검증을 처리할 수 도 있다.

/*        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }*/
        //다음과 같이 try-catch문을 이용해서 검증을 할 수도 있다.

        //이런식으로 짜면 validate에 걸려서 터져야 한다.

        //then
    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}