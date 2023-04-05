package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello라고 치면 이 메서드를 호출해 준다.(http get요청과 비슷)
    public String hello(Model model){
        model.addAttribute("data","hello!"); // 여기에서 data는 키이고 hello는 벨류이다
                                                                    // hello.html에서 템플릿 엔진 th에 data가 hello로 치환된다.
        return "hello";                                             // 웹브라우져에 hello를 치면 templates/hello/html을 찾아서 템플릿 엔진이 처리한다.

    }
    @GetMapping("hello-mvc")
        public String helloMvc(@RequestParam("name") String name,Model model)//외부에서 파라미터를 받는다. 그리고 모델에 받으면 view에서 사용
    {
        model.addAttribute("name", name);//addatrribute에서 파라미터로 넘어온 name을 넘긴다. "name"은 키다.
        return "hello-template";                     //만약 웹브라우져에서 localhost:8080/hello-mvc?(get방식)name=spring!을 하면 name이 spring!으로 바뀌고 모델이 담긴다음 템블릿으로 넘어가면 모델이 키값인 것에서 spring을 꺼내서 출력한다.
    }
    @GetMapping("hello-string")
    @ResponseBody               //http에서 응답 body에 "hello" + name 을 직접넣겠다는 의미이다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // name에 spring을 넣으면 hello spring이 나온다.
    }

    //데이터를 받기위한 api 방식
    @GetMapping("hello-string")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();  //객체를 만들어줌
        hello.setName(name);
        return hello; //객체를 넘겨줌, 객체를 넘겨줄시 제이슨 방식으로 바꿔서 http body에 넘겨준다.
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;   //꺼낼 때는 getname
        }


        public void setName(String name) {
            this.name = name;   //넣을 때는 setname
        }
    }

}
