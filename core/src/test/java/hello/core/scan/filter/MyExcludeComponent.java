package hello.core.scan.filter;


import java.lang.annotation.*;

@Target({ElementType.TYPE}) //애너테이션이 적용가능한 대상을 지정하는 사용한다. elememt타입에 적용을 할 수 있다라는 뜻
@Retention(RetentionPolicy.RUNTIME) //애너테이션이 유지되는 범위를 지정하는데 사용한다. 런타임까지 사용가능하다.
@Documented //애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다.
public @interface MyExcludeComponent {
}
