package hello.core2.discount;

import hello.core2.member.Grade;
import hello.core2.member.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DiscountPolicyTest {


    @Test
    void rateDiscountPolicy(){
        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        Member member = new Member(1L, "userA", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @Test
    void fixDiscountPolicy(){
        FixDiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
        Member member = new Member(1L, "userA", Grade.VIP);
        int discount = fixDiscountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(1000);
    }


}