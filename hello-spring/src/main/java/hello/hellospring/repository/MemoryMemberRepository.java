package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository //스프링컨테이너에서 repository라고 등록을 시켜준다.
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store =new HashMap<>();
    //첫 번째 인자는 식별자, member는 회원이름
    private static long sequence =0L;   //식별자 아이디
    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //sequence값을 하나 올린다.
        store.put(member.getId(),member);   //ID값을 넣는다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //값이 null로 반환될 경우를 대비해서Optional로 감싸서 반환하면 클라이언트에서 뭘 할 수가 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //getName과 name이 같은지 filter한다. stream중간연산
                .findAny(); //하나라도 찾는다. stream최종연산
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        //저장된 데이터를 지운다.
    }
}
