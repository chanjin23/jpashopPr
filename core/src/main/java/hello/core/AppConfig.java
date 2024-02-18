package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;    // import가 잘되어있는지 확인한다.

//DI 컨테이너란 말그대로 의존주입을 해주는 애들을 DI컨테이라고 불린다.
//이 구성들에서 DI컨테이너는 AppConfig가  의존주입을 대신해주고있기 때문에 AppConfig가 DI 컨테이너라고 할 수 있다.
@Configuration  //스프링에서는 설정정보에 @Configuration이라고 적게되어있다.
public class AppConfig {
    //앱 전체를 설정하고 구성하는 클래스
    //AppConfig에는 역할에 따른 구현이 잘안보인다.
    //각각 역할에 따른 구현이 잘보이게끔 따로따로 분리를 시킨다.
    //이렇게하면 내가 할인율 정책을 바꾸든 회원 저장소를 바꾸든 그 부분만 따로 빼서 할 수 있기때문이다.
    //만약 내가 할인정책을 바꾸려고 한다. 예전에는 구현체에 직접들어가서 코드를 변경해야했지만
    //Config를 구성함으로써 사용영역에 코드는 아예건들지 않고 구성영역(Config)만 건들면된다.
    //즉, OCP와 DIP원칙을 잘 지키고 있다.

    @Bean   //Configuration안에 Bean을 구성하면 자동으로 스프링안에서 스프링 빈으로 등록이된다.
    //등록된 스프링빈은 ApplicationContext를 통해서 꺼내서 사용할 수있다. 이때 스프링빈은 해당 메서드의 이름으로 등록을 시키며
    //만약 스프링빈의 이름을 바꾸고싶을때는 (name =" ")를 통해서 바꾸면 된다.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");     //호출로그를 남기기 위해 생성
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public  MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");  //호출 로그를 남기기 위해 생성
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");  //호출 로그를 남기기위해 생성
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.setDiscountPolicy(discountPolicy());
//        orderService.setMemberRepository(memberRepository());
//        return orderService;

    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
        //OCP, DIP원칙을 잘 지켰기 때문에 다음과 같이 AppConfig에서 코드를 바꿔도 문제가 발생하지 않는다.
    }


}
