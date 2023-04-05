package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service  //spring이 올라올때 서비스로 인식하고 컨테이너에 멤버서비스를 등록해준다.
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository() ;




    ;



    /**
         * 회원가입
         */


        public Long join(Member member) {

            //같은 이름이 있는 중복회원이 있으면 안된다.
            vaildateDuplicateMember(member); //중복회원 검증
            memberRepository.save(member);
            return member.getId();    //회원가입을 하면 id를 반환해준다.
        }

        private void vaildateDuplicateMember(Member member) {
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        }
        /**
         * 전체 회원 조회
         */
        public List<Member> findMember(){
            return memberRepository.findAll(); //반환타입이 listmember이다.
        }
        public Optional<Member> findOne(Long memberId){
            return memberRepository.findById(memberId);
        }




}
