package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository  //컨트롤러를 통해서 외부 요청을 받고, 서비스에서 비지니스 로직을 만들고, 레포지토리에서 저장을 하는 역할
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();// 키는 회원의 아이디 값이므로 Long이다
    private static  long squence = 0L; // squence는 키값을 생성해주는 역할
    @Override
    public Member save(Member member) {
        member.setId(++squence);   //id를 +해준다음 store에 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //맵에서 돌면서 하나라도 찾아지면 바로 반환, 만약 없으면 optinal에 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values는 멤버들이다.
    }
    public void clearstore(){
        store.clear();
    }
}
