package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
//    MemberService memberService = new MemberServiceImpl();
    //구현체에 직접 의존했기 때문에 DIP원칙 위배
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
//        MemberService memberService1 = appConfig.memberService();
//        memberService = memberService1;
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(findMember).isEqualTo(member);

        //junit Test를 이용하여 정상적으로 잘작동함.
    }
}
