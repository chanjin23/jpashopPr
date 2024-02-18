package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장한다
    Optional<Member> findById(Long id);    //아이디를 찾는다.
    //null을 처리하는 방법중에 null을 그대로 나오기 전에 Optional을 감싸서 처리하는경우가 많다.
    Optional<Member> findByName(String name);   //이름을 찾는다.
    List<Member> findAll(); //지금까지 모든 회원의 리스트를 반환한다.
}
