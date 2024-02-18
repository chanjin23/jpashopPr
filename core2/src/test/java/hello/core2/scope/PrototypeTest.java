package hello.core2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);


        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        //의존관계주입 -> 초기화 메서드 실행

        System.out.println("find prototypeBean");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        //의존관계 주입 -> 초기화 메서드실행

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        //싱글톤 패턴과 다르게 프로토타입은 조회할 때마다 새로운 인스턴스를 생성하여 의존관계를 주입한다.

        ac.close();
        //프로토타입은 스코프의 범위가 빈 생성, 의존관계 주입 그리고 초기화까지만 관여를 한다. 종료메서드에는 관여를 하지 않는다.
    }

    @Scope("prototype")
    static class PrototypeBean {

        public PrototypeBean(){
            System.out.println("생성자");
        }
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
