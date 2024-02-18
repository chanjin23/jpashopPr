package hello.core2.singleton;

import hello.core2.AppConfig;
import hello.core2.order.OrderService;
import hello.core2.order.OrderServiceImpl;
import hello.core2.repository.MemberRepository;
import hello.core2.service.MemberService;
import hello.core2.service.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        //로그를 확인해보면 빈을 조회할때마다 한번씩만 인스턴스가 호출되는 것을 확인 할 수 있다.

        System.out.println("memberService -> memberRepository =" + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository =" + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);
        //모두 같은 인스턴스를 참조하고 있는것을 확인할 수 있다.

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        //Configuration을 지우면 싱글톤 패턴이 적용되지 않을 것을 확인할 수 있다.
        //Bean만 사용해도 스프링컨테이너에 등록되지만 싱글톤패턴이 적용되지 않는다.
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        //AppConfig도 스프링 빈으로 등록 가능

        System.out.println("bean = "+bean);
        //bean = hello.core2.AppConfig$$SpringCGLIB$$0@5c3b6c6e
        //뒤에 SpringCGLIB가 생성된것을 확인 할 수 있다. -> 바이트코드 조작
    }
    //CGLIB 예상코드
    /*@Bean
    public MemberRepository memberRepository() {

        if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
            return 스프링 컨테이너에서 찾아서 반환;
        } else { //스프링 컨테이너에 없으면
            기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
            return 반환
        }
    }*/
}
