package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ClassBasedNavigableIterableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();
    @AfterEach   //메서드가 끝날때마다 실행되는것 save, find등이 끝날 때 마다 store를 비워준다.
    public void afterEach() {
        MemoryMemberRepository repository;
        repository.clearstore();

    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then 검증
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_제외(){
        //given
        Member member1 =new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // memberService.join(member1); //예외를 터지게 한다.
 /*
        try {
            memberService.join(member2);
            fail();
            }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미존재하는 회원입니다.");
        }

  */
    }



}