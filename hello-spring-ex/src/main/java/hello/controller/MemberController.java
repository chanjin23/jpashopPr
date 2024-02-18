package hello.controller;

import hello.domain.Member;
import hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //get방식은 url에다가 직접 치는 것을 말한다. 보통 조회할 때 get방식을 사용한다.
    public String createForm() {
        return "members/createMemberForm";  //return을 하면 template에서 해당 html파일을 찾게된다.
    }

    @PostMapping("/members/new")    //post방식은 데이터를 form(태그)같은데 넣어서 전달할 때 쓰게 된다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";    //원래 홈페이지로 넘어가게 된다.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
