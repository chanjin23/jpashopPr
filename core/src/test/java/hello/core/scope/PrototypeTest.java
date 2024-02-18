package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        //프로토타입은 빈을 호출할때마다 그때그때 불러오게됨

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
        //프로토타입은컨테이너 생성, 의존관계주입 그리고 초기화까지만 관여를 하기 때문에
        //스프링컨테이너가 종료될떄 @PreDestroy같은 종료메서드가 전혀 실행되지 않는다.

    }

    @Scope("prototype") //default값
    //참고로 componentScan을 사용해야 스프링컨테이너에 들어가지만,
    //AnnotationConfigApplicationContext안에 인자가 들어가게되면 스프링컨테이너로 인식되서 객체를 생성하게 된다.
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
