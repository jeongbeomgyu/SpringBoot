package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //컴포넌트 스캔으로 스프링 빈을 설정하는 방법
public class MemberController {
    private final MemberService memberService;

    @Autowired //Memberservice를 스프링이 스프링 컨테이너에 있는 memberservice를 가져다가 연결시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
                                                //생성자를 통해서 memberservice가 membercontroller에 들어온다.(생성자 주입)
    }
    //회원기능 등록
    @GetMapping("/members/new")
    public String crateForm(){
        return "members/createMemberForm";  //resource에서 createMemberForm으로 이동한다.
    }
    @PostMapping("/member/new")  //데이터를 등록할때는 post 조회할 때는 get을 사용한다.
    public String create(MemberForm form) {
        Member member =new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; //끝났으니 홈 화면으로 보내준다.
    }
    //회원기능 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("member", members); //member리스트 즉,모든멤버에 대한 정보를 모델에 담아서 view에다가 넘긴다. 단 메모리에 저장되기 때문에 서버를 끄면 데이터가 다 삭제 된다.
        return "members/memberList";  //template에 있는 memberlist로 이동
    }
}
