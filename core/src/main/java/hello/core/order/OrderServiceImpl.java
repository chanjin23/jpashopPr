package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  //@Component를 붙여 스프링빈에 등록되게 한다.
//@RequiredArgsConstructor    //final키워드가 붙은 멤버변수가 있으면 생성자를 자동으로 생성해주고 주입해준다.
public class OrderServiceImpl implements OrderService{
//    private final MemberRepository memberRepository=new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //현재 내가 만든 서비스에는 두가지를 받아와야한다. 할인정책과 아이디
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired  //setter 주입
//    //Autowired는 자동으로 자기가 알아서 주입을 시켜준다.
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy= discountPolicy;
//    }

    @Autowired
    //스프링 컨테이너는 스프링 빈을 등록한다음에 의존주입이 일어난다. -> setter주입
    //근데 생성자주입은 인스턴스 객체를 불러오기 때문에 스프링빈을 등록함과 동시에 주입이 일어난다.
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //다음과 같이 생성자를 통해서 주입을 시켜주면 구현체를 의존하지 않고도 추상만 의존하여 구현할 수 있다.
    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
        //회원정보 찾기 리모컨과 할인정책 리모컨 2개를 합쳐서 주문을 한것이다.
        //이것은 현재 구현체에 의존하는 것이 아닌 추상에 의존하고 있는 코드를 작성한 것이므로 좋은 코드라고 볼 수 있다.
        //이렇게 함으로써 코드를 변경할 때 다른 코드로 금방 대체를 할 수 있다.
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
