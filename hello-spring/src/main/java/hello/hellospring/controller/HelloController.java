package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")    //http localhost:8080 /get hello매칭이 된다.
    public String hello(Model model) {  //model의 값을 넣는다.
        model.addAttribute("data","hihi"); //"data"라는 키값이 "hello"로 치환된다.
        return "hello"; //template에 있는 hello 파일을 찾아가서 렌더링 해라 이렇게 컨트롤러에서 리턴값을 반환하는 것을 뷰리졸버라고한다.
        //뷰리졸버가 알아서 화면을 찾아서 처리한다.(렌더링)
        // 그럼 스프링 부트에서 템플릿엔진이 알아서 찾아서 처리해준다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name" )String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    //notation을 쓰는 이유는 http를 쓸때 값을 넘기기 위해서 @를 쓰는것이다.


    @GetMapping("hello-string")
    @ResponseBody //http에서 헤더부와 바디부가 있는데 바디부의 데이터를 내가 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello " +name;  //name에 spring을 넣으면 hello spring을 넣는다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello =new Hello();
        hello.setName(name);
        return hello;
    }
    //이런 식으로 구성을 하면 json으로 하게 된다.
    //responsebody를 쓰면 원래는 바로 리졸버에 바로넘겼는데 지금은 HttpMessageConverter가 작동을하면서 변환을함
    //근데 문자열이 아니라 객체가 오면 어떻게 될까 -> json형식으로 바꿔버림.

    //정리하면 정적 컨텐츠는 html을 파일을 그대로 요청한대로 내보낸다
    //MVC(Model View Controller)방식은 Controller에 있는 로직들을 이용하여 View에게 넘겨서 반환해 http에 띄우는 방식
    //API 개발자 입장에서 API는 객체를 반환하여 넘겨주는 방식을 말한다.

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
