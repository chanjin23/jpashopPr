package hello.core2.web;

import hello.core2.common.MyLogger;
import hello.core2.logdemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;    //의존관계 주입이 일어남 -> MyLogger는 스코프가 request이다.
    //request의 생존범위는 고객의 요청이 들어오고 나오기까지의 범위이다.
    //그런데 고객의 요청이들어오지를 않으니  오류가 난다.

    @RequestMapping("log-demo")
    @ResponseBody   //뷰 화면이 없으므로 ResponseBody로 바로 반환하게한다.
    //원래는 뷰리졸버에게 템플릿을 넘긴다.
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        MyLogger myLogger = myLoggerProvider.getObject();// getObject를 호출시키게해서 request 요청을 한다.
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL); //request 시작

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");     //request 요청 마지막
        //request close
        //request 요청이 끝남
        return "OK";
    }
}
