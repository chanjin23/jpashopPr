package hello.core2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithProtoTest3 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        //싱글톤 스코프 클래스에 프로토타입 클래스가 주입받은상태이다.
        //여러개의 인스턴스를 만들지 못하고있는 상황 -> 같은 인스턴스를 공유하게됨

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.logic();
        Assertions.assertThat(logic1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int logic2 = clientBean2.logic();
        Assertions.assertThat(logic2).isEqualTo(1);


    }

    @RequiredArgsConstructor
    static class ClientBean{
        private final ApplicationContext ac;

        public int logic(){
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            //logic이 호출될때마다 빈 조회를 하므로 새로운 인스턴스를 생성함
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count =0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init =" + this);  //자기 자신의 참조변수를 출력
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
