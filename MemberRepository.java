package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  //저장소에 저장소에 저장됨
    Optional<Member> findById(Long id);  //저장소에서 id를 찾아옴
    Optional<Member> findByName(String name);
    List<Member> findAll(); //지금까지 저장된 모든 회원의 정보를 반환해줌
}
