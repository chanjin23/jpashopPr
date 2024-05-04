package jpabookPr.jpashopPr.service;

import jpabookPr.jpashopPr.domain.Member;
import jpabookPr.jpashopPr.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //모든 JPA는 트랜잭션안에 돌아가야 한다.
@RequiredArgsConstructor //final에 있는 필드만 생성자 주입을 만들어준다. (Lombok)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *회원가입
     */
    @Transactional  //default가 readOnly = false
    public Long join(Member member) {
        validateDuplicateMember(member);    //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        //member.getName()을 유니크 제약조건으로 사용하는 것이 좋다.
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
