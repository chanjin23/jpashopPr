package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component  //Component를 통해 스프링빈에 등록했다.
@Primary
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository =new MemoryMemberRepository();
    //여기서 문제점은 MemberServiceImpl은 추상화에만 의존해야 하는데 MemoryMemberRepository 즉, 구현체에도 의존하고 있는상황이다.
    //DIP원칙을 위배한 것이다.
    private final MemberRepository memberRepository;


    // Autowired를 사용하기 위해선 스프링빈에 등록 되어있어야한다.
    @Autowired  //생성자가 하나만 있으면 자동으로 Autowired를 생성해준다.
    //그 전에는 Bean에서 직접 주입을 해줬다. 그런데 여기는 주입해주는 설정정보가 없다.
    //@Autowired는 자동으로 주입을 하게 해준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //다음과 같이 생성자를 통해서 주입을 시켜주면 구현체를 의존하지 않고도 추상만 의존하여 구현할 수 있다.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
