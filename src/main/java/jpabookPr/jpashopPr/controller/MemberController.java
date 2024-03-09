package jpabookPr.jpashopPr.controller;

import jpabookPr.jpashopPr.domain.Member;
import jpabookPr.jpashopPr.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        //여기서 Model은
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
}
