package hello.core2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithProtoTest5 {

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
        private final Provider<PrototypeBean> provider;
        //방법 3. Provider 이용 ,단 별도의 라이브러리 필요

        public int logic(){
            PrototypeBean prototypeBean = provider.get();   //get메서드를 사용하여 좀더 간편하게 사용할 수 있다.
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
