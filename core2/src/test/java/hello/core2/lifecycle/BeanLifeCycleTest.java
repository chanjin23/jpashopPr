package hello.core2.lifecycle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


public class BeanLifeCycleTest {


    @Test
    void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient bean1 = ac.getBean(NetworkClient.class);  //조회를 한다고해서  생성자, 초기화, 종료를 한게 아님.
        NetworkClient bean2 = ac.getBean(NetworkClient.class);
        Assertions.assertThat(bean1).isInstanceOf(NetworkClient.class);
        Assertions.assertThat(bean2).isInstanceOf(NetworkClient.class);
        ac.close();

    }

    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
