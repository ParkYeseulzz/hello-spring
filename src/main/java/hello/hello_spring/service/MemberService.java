package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //스프링이 올라올때 컨트롤러에 멤버서비스를 등록해줌
public class MemberService {
    /*ctrl + shift + t를 누르면 새테스트케이스 작성가능*/


    //먼저 멤버 저장소가 필요함
    private final MemberRepository memberRepository;
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    //회원가입
    public Long join(Member member) {
        //같은 이름의 중복회원은 안된다.
        //optional은 null일 가능성이 있으면 감싸서 사용하면 되고, optional 말고 다른 방법은
/*        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        //이런 방법도 있음
        //Ctrl + Alt + M
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    //전체회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
