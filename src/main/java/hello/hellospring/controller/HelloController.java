package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")//get post 에서의 get 이라 생각하세요.
    public String hello(Model model){
        model.addAttribute("data","인석천재!!");//model(data:"hello!!")
        return "hello";//resources/templates/hello.html 로 가서 rendering 해라 라는뜻.
    }

    @GetMapping("hello-mvc")
    public String helloMvc( @RequestParam("name") String name, Model model){
        model.addAttribute("name", name);// model 이게 html 파일에서 키값을 꺼내는거래.
        //@RequestParam("name") String name 이 부분이 이해가 좀 안되긴 함.
        //21.05.13 이해 했다. onenote 에도 적음
        //RequestParam("name") : localhost:8080/hello-mvc?name 에서의 name
        //R() String name : local host://hello-mvc?name=inseok 에서의 inseok
        //model.addAtrribute(attributeName:"name",name);  name은 inseok, "name" 은 .html 에서 ${name}

        return "hello-template";
    }

    //response 문자 반환
    @GetMapping("hello-string")
    @ResponseBody //html의 body tag 가 아니라 http 의 body : HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님
    public String helloString(@RequestParam("name")String name){
        return "hello" + name;
    }

    //response 객체 반환
    @GetMapping("hello-api")
    @ResponseBody// 거의 이렇게 쓰는건 JSON 이 기본이 됐을 정도라네? //XML  도 되긴함
    public Hello helloApi(@RequestParam("name") String name) {//JSON 형태
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //클래스 Hello의 객체 hello 를 반환?(JSON 형태)
    }

    static class Hello{//getter and setter : properties 접근방식 ([alt + insert])
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
