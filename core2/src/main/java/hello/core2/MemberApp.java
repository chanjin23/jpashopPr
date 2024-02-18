package hello.core2;

import hello.core2.member.Grade;
import hello.core2.member.Member;
import hello.core2.repository.MemberRepository;
import hello.core2.repository.MemoryMemberRepository;
import hello.core2.service.MemberService;
import hello.core2.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        Member member = new Member(1L, "memberA", Grade.VIP);

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member =" + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
