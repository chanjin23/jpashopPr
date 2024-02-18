package hello.core.autowired;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)    //required의 기본값은 true이다. required를 false 상태로하면 아예 호출이 안된다.
        public void setNoBean1(Member noBean1/*스프링 컨테이너에 관련없는 Member를 주입 시킨 상태*/){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2/*스프링 컨테이너에 관련없는 Member를 주입 시킨 상태*/){
            //Member는 스프링컨테이너에 없는 애이므로 null을 반환 이떄 Nullable을 통해서 null을 반환한다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3/*스프링 컨테이너에 관련없는 Member를 주입 시킨 상태*/){
            //Optional은 해당 값이 null일때 Optional.empty로 반환한다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
