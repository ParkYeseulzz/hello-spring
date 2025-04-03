package hello.hello_spring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//스프링은 @컨트롤러 어노테이션 해줘야한다.
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data",  "hello!!");
        return "hello";
    }

    //control+p 하면 옵션을 넣을 수 있다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")String name, Model model){
        model.addAttribute("name", name);
        return"hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody //HTTP에서 BODY에 이 응답을 직접 넣어주겠다. (페이지 소스보기하면 HTML없음)
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; //hello spring

    }

    @GetMapping("hello-api")
    @ResponseBody //기본으로 json으로 반환 `@ResponseBody` 를 사용하면 뷰 리졸버( `viewResolver` )를 사용하지 않음
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; //키 값이 name

        //ALT + Insert getter/setter단축키
        //꺼낼때
        public String getName(){
            return name;
        }
        //등록할 때
        public void setName(String name){
            this.name = name;
        }
    }
}
