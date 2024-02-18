package hello.service;

import hello.domain.Member;
import hello.repository.MemberRepository;
import hello.repository.MemoryMemberRepository;
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
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.memberClear();
    }
    @Test
    void 회원가입() {
        //given
        Member member= new Member();
        member.setName("spring1");

        //when
        memberService.join(member);

        //then
        Member result = memberService.findOne(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void 회원_중복_예외(){
        Member member1= new Member();
        member1.setName("spring1");

        Member member2= new Member();
        member2.setName("spring1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

//        try{
//            memberService.join(member2);
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }


    }

    @Test
    void findMembers() {
    }
}