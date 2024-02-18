package hello.core.web;

import hello.core.common.MyLogger;

import hello.core.logdemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor    // setter 쉽게
public class LogDemoController {
    private final LogDemoService logDemoService; //생성자 주입
    private final MyLogger myLogger; //생성자 주입

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString(); //HttpServletRequest를 통해서 요청 URL을 받았다.
//        MyLogger myLogger = myLoggerProvider.getObject();
        //http://localhost: 8080/log-demo
        myLogger.setRequestURL(requestURL); //저장

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
