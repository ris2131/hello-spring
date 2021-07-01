package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//spring이 실행될때 spring container 통이 생기는데 에 spring 이 MemberController 라는 객체를 만들어서 관리 한대.//스프링 빈이 관리된다라고 한다네
public class MemberController {

    //private final MemberService memberService = new MemberService();//new 이렇게 하면 여러 서비스가 겹치면서공용으로 쓰면되는데 중복.
    private final MemberService memberService;

    @Autowired//생성자에서쓰는.. memberService 를 가져와서 연결 시켜주는 : Dependency injection (DI) : 밖에서 넣어주는.
    public MemberController(MemberService memberService) {//빨간불 안뜨는데?
        this.memberService = memberService;
    }
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")//post? : html 에서 변경을 시키려 할때, get : 조회할때
    //get, post 공부하자.
    public String create(MemberForm form) {//MemberForm.java 이걸 어떻게 아는거지? 다른 파일인데?
        Member member = new Member();
        member.setName(form.getName());


        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";//home 화면으로 돌려주는.
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);//model 에다가 key가 members 라는애의 members의 list 를 집어 넣어줌

        return "members/memberList";

    }

}
