package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)   //scope범위가 request 서비스 요청부터 응답까지의 범위
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String messsage){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + messsage);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();    //uuid로 다른 request 요청이 랜덤하게 들어온다.
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }

}
