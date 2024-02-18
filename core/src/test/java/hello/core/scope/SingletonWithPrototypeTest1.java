package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        assertThat(clientBean2).isSameAs(clientBean1);
        //싱글톤객체에서 조회를 한상황 -> 싱글톤객체는 생성자주입을 했으므로 컨테이너 생성시 바로 의존관계주입발생
        //의존주입을 할때 주입하는 객체가 프로토객체 -> 프로토객체 인스턴스 생성-> 즉 프로토타입은 조회를 할때마다 그때그때 생성되야하는데
        //주입이 된시점부터 프로토타입은 인스턴스를 생성하지못하고 하나의 객체만 사용하게 된다.

    }
    @Test
    void singletonClientUsePrototype2(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class,PrototypeBean.class);
        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        assertThat(clientBean2).isSameAs(clientBean1);
        //싱글톤 빈이 프로토타입을 사용할 때마다 스프링컨테이너에 새로 요청하는 것
    }

    @Scope("singleton")
    static class ClientBean2{
        //해결방법은 1. Application 직접 생성
        @Autowired ApplicationContext ac;

        public int logic(){
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();   //Ctrl + Alt + n으로 코드를 합칠 수 있다.
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> provider;

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeans;

//        private final PrototypeBean prototypeBean;  //생성시점에 주입이 된상태이다.  생성자 주입을 통해서 주입됐기때문에
//
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();   //Ctrl + Alt + n으로 코드를 합칠 수 있다.
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
