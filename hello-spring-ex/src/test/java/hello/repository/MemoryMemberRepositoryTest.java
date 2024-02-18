package hello.repository;

import hello.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        memberRepository.memberClear();
    }
    
    @Test
    void save() {
        //given
        Member member= new Member();
        member.setName("spring1");
        
        //when
        memberRepository.save(member);
        
        //then
        Member result = memberRepository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);

    }

    @Test
    void findByName() {
        Member member =new Member();
        member.setName("spring1");

        memberRepository.save(member);

        Member result = memberRepository.findByName("spring1").get();
        assertThat(result).isEqualTo(member);

    }

    @Test
    void findAll() {
        Member member1 =new Member();
        member1.setName("spring1");

        Member member2 =new Member();
        member2.setName("spring2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }
}