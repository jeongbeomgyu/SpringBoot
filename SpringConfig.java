package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean //스프링 빈을 등록하겠다는 의미
    public MemberService memberService(){
        return new MemberService(); //스프링이 뜰 때 이 configuration을 읽고 이건 스피링 빈에 등록하라는 뜻으로 인식을 한다.
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
