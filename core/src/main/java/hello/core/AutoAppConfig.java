package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
//컴포넌트 스캔을 사용하려면 먼저 @ComponentScan을 설정정보에 붙여주면 된다.
@ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //참고로 컴포넌트스캔을하면 component에 관련된 모든요소를 자동으로 등록시켜버리는데 AppConfig에 @Configuration은 @Component가 있어서
        //함꼐 등록되어 버린다. 그래서 excludeFilters를 이용해 컴포넌트 스캔대상에서 제외했다.
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
    //수동으로 @Component에서 스프링빈 이름과 충돌되는 상황
    //이 경우에는 수동 빈 등록이 우선권을 가진다. -> 수동 빈이 자동 빈을 오버라이딩 해버린다.
    //근데 이렇게 해버리면 잘못하다가 기존에 구현했던 코드는 날라갈 수 있기때문에 실무에서 크나큰 문제가 될수있다.
    //이런 버그들은 나중에 문제가 생기면 정말로 잡기 힘들다.
    //그래서 ComponentScan이 아닌 SpringBootApplilcation도 ComponentScan기능이 있는데 이친구는 아예 오류를 잡아버리게끔해
    //개발자의 실수를 줄이도록한다.
}
