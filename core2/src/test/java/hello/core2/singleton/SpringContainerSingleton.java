package hello.core2.singleton;

import hello.core2.AppConfig;
import hello.core2.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContainerSingleton {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
        //같은 인스턴스를 참고하고있음
    }
}
