package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
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

        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " +memberRepository);
        //각각 서로 다른 객체에서 memberRepository 값을 생성해도 같은 참조 변수값을 참고하고 있는 것을 알 수 있다.

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
    
    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);   //AppConfig도 스프링빈에 등록되므로 확인해본다.
        System.out.println("bean = " + bean.getClass());    //Object클래스에 있는 메서드 getClass()
        //원래는 AppConfig 스프링빈이생성되어야되는데 AppConfig$$SpringCGLIB$$0가 생성되서 뒤에 뭐가 붙은것을 확인 할 수 있다.
        //이것은 @Configuration이 CGLIB라는 바이트 코드 조작 라이브러리를 사용해서 AppConfig클래스를 상속받은 임의의 다른 클래스를 만들고,
        //이 클래스를 스프링빈으로 등록한 것이다. CGLIB 바이트 코드로 조작된 클래스는 덕분에 싱글톤을 보장 받을 수 있다.
        //만약 @Configuration이 없는 상태에서 스프링빈을 등록하면 스프링컨테이너에 등록은 되지만
        //순수 자바코드로 동작하는 것이기때문에 싱글톤을 보장 받지못한다.
    }
}
