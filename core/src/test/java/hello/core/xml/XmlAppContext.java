package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class XmlAppContext {
    @Test
    void xmlAppContext(){
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
    //xml파일을 보여준이유는 이와같이 스프링은 자바코드, XML그외에도 Groovy등등 다양한 설정 형식을 지원해준다.
    //최근에는 스프링 부트를 많이 사용하면서 XML기반의 설정은 잘 사용하지 않는다.
    //그래도 아직 많은 레거시 프로젝트들이 XML로 되어있기 때문에 한번 쯤 살펴보는것도 나쁘지않다.
    //XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있다는 장점도 있다.
}
