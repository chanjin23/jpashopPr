package hello.core2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


public class SingletonTest {
    @Test
    public void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);    //스프링 빈 생성, 의존관계 주입, 초기화 콜백, 소멸전 콜백

        //사용
        System.out.println("find prototypeBean1");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("find prototypeBean2");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
        //싱글톤 패턴은 스프링컨테이너가 생성-> 스프링 빈 생성 -> 의존관계 주입(Autowired) -> 초기화 콜백(@PostConstruct) -> 사용 -> 소멸전 콜백(@preDestory) -> 스프링 종료
    }


    @Scope("singleton")
    static class SingletonBean{

        public SingletonBean(){
            System.out.println("생성자");
        }
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destoy(){
            System.out.println("SingletonBean.destoy");
        }
    }
}


