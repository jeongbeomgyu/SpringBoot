package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.io.ObjectInputStream;
import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach   //메서드가 끝날때마다 실행되는것 save, find등이 끝날 때 마다 store를 비워준다.
    public void afterEach() {
        repository.clearstore();

    }


    @Test //save기능이 동작하는지 확인한다.
    public void save() {
        Member member =new Member();
        member.setName("spring");

        repository.save(member); //리포지토리에 member를 save한다.
        Member result = repository.findById(member.getId()).get(); //내가 넣은 객체가 들어갔는지 확인, 반환타입이 optional이므로 get으로 값을 꺼낸다
        Assertions.assertEquals(member, result);


    }
    @Test   //이름으로 찾는 것을 테스트 해야한다.
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //spring1, spring2 회원이 가입이 된것이다.

        //findbyname이 잘 작동되는지 확인
        Member result = repository.findByName("spring1").get();
        Assertions.assertEquals(member1, result);


    }
    //findall에 대한 test
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(member1, result);
    }


}
