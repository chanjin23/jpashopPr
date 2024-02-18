package hello.core.beandefintion;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    //해당 방식은 자바코드로 값을 가져오는방식
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    //xml파일을 불러들어와서 하는방식

    //둘 방식다 beanDefintion으로 추상화하기 때문에 스프링컨테이너에는 똑같은 방식으로 스프링 빈이 구성되게 된다.

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        //BeanDefinition은 하나의 추상화로 스프링이 다양한 형식을 지원해주게 한다.
        //사실 스프링컨테이너는 class로 직접 설정정보를 가져오는 것이아닌 BeanDefinition에서 메타정보를 가져와 스프링 빈을 생성한다.
        //자바코드나 xml파일이나 BeanDefinition에게 넘겨서 값을 메타정보로 바꾼다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //BeanDefinition의 메타정보 이름들을 전부 꺼내온다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            //반복문을 돌리면서 스프링 빈에있는 정보들을 getBeanDefition으로 값을꺼낸다

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                //이때 beanDefinition에 있는 정보들을 전부꺼내면 스프링에 있는 모든 정보들이 나오므로 ROLE_APPLICATION 명령어로 내가 직접 만든 스프링빈만 호출되게끔 한다.
                System.out.println("beanDefinitionName = " + beanDefinitionName + ", beanDefinition = " + beanDefinition);
            }
        }
    }
}
