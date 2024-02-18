package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Junit은 테스트를 할때 lifecycle이 존재한다.
//테스트코드가 실행되기전에 beforeEach가 실행되고 테스트코드가 실행되고,,,
//이처럼 우리가 테스트코드를 작성할 때 필요한 부분에다가 직접 주입을 시켜주고 하는데
//이때 프레임워크가 적절한타이밍에 딱 라이프사이클대로 돌면서 프레임워크에게 제어흐름을 담당하는 것을 제어의 역전이라고 한다.
//메인메서드같은경우에는 개발자가 순차적으로 직접 제어의 흐름을 담당한다. 이것은 프레임워크가 아니라 라이브러리이다.

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    //Long 대신에 long을 사용해도 상관없으나 NULL을 사용할 수  없기때문에 Long을 사용한다.

    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
        //메서드 안에 코드를 작성해야 하기때문에 BeforeEach즉, 테스트코드가 실행되기전 하나씩 무조건 실행되는
        //애너테이션안에 메서드를 작성하여 appConfig를 통해 주입한다.
    }



    @Test
    public void creatOrder(){
        Long memberId =1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
