package hello.core2.beanfind;

import hello.core2.discount.DiscountPolicy;
import hello.core2.discount.FixDiscountPolicy;
import hello.core2.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationConfigContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> typeOfBeans = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : typeOfBeans.keySet()) {
            DiscountPolicy bean = typeOfBeans.get(key);
            System.out.println("key = " + key + ", value =" + bean);
        }
        System.out.println("typeOfBeans = " + typeOfBeans);

        Assertions.assertThat(typeOfBeans.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 -Object")
    void findAllBeanByObjectType(){
        Map<String, Object> typeOfBeans = ac.getBeansOfType(Object.class);
        for (String key : typeOfBeans.keySet()) {
            Object bean = ac.getBean(key);
            System.out.println("key=" + key + ", value=" + bean);
        }
        System.out.println("typeOfBeans= "+ typeOfBeans);
//        Assertions.assertThat(typeOfBeans.size()).isEqualTo();
    }


    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
