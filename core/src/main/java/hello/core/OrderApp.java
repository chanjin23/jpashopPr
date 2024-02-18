package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = appConfig.memberService();
        //이전에는 직접 인스턴스를 생성하여 구현했지만 DIP원칙을 통해서 AppConfig를 통해서 하게 되면
        //DIP에 위배되지 않고 구현이 가능하다.

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링에서 값을 꺼내올때는 ApplicationContext(스프링컨테이너)를 사용해야한다.
        //AnnotationConfigApplicationContext는 Configuration이 있는 문장을 찾는다. 우리는 APpConfig에서 구성을 했기때문에 인자로 AppConfig를 넣는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //Configuration안에 있는 스프링빈에서 Bean을 꺼내온다. 이때 스프링빈의 이름을 값에 넣어서 갖고 오게된다.
        //두번째 인자는 받아올 타입을 적어준다.
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
        //toString으로 오버라이딩을 시켰기 때문에 쉽게 결과를 도출할 수 있다.
        //이것 역시 메인메서드로 했기때문에 바람직한 테스트코드가 아니다.
        //junit에서 제공해주는 Test를 이용하는 것이 낫다.
    }
}
