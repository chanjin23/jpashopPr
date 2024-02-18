package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //임의의 멤버를 하나만들어서 서비스 리모컨을 이용해 잘 작동하는지 확인하는 코드
//        MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());
        //서비스 리모컨이 잘 작동하는지 확인하기 위해서 테스트
        //이것은 정말 순수하게 자바로 개발을 한 상황
        //그런데 이렇게 메인메서드로 직접확인하는방법은 좋은 방법이아니다.
        //그래서 junit 인 테스트애너테이션을 이용하면 편하다.
    }
}
