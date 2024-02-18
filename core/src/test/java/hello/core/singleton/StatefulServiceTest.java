package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA: 10000원 주문
//        statefulService1.order("userA",10000);
        //ThreadB: 20000원 주문
//        statefulService2.order("userB",20000);

        int userA = statefulService1.order("userA", 10000);
        int userB = statefulService2.order("userB", 20000);

//        System.out.println("statefulService1 = " + statefulService1.getPrice());
        //개대했던 가격은 10000원이 나와야하는데 20000원이 나와버림
        //상태변수를 같이 공유했기 때문에 생기는문제
        //이렇기 때문에 공유필드는 정말로 조심해야한다.
        //스프링 빈은 항상 무상태(stateless)로 설계해야한다.

        System.out.println("statefulService1 = " +userA);
        //다음과 같이 return값을 반환해서 넘겨버리면 해결할 수 있음
        //여기서는 간단하게 하기위해 쓰레드를 사용하지 않았지만 실무에서는 멀티쓰레드 즉, 동시성문제
        //들이 발생한다. 이러한 부분은 정말로 큰 장애를 발생하니 공유필드를 무상태로 만들어야 한다.
    }

    @Configuration
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }


}