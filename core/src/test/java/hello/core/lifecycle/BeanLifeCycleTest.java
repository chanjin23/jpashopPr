package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //ApplicationContext에 하위 인터페이스로 ApplicationContext가 가지고 있지 않은 기능들을 가지고 있다.
        NetworkClient bean = ac.getBean(NetworkClient.class);
        ac.close(); //스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
        //스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
        //그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까?
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean/*(initMethod = "init",destroyMethod = "destroy")*/
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();  //객체를 생성하는 과정에는 url이 없다.
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
