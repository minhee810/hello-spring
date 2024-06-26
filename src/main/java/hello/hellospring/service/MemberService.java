package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     * @return
     */
    public Long join(Member member) {
        // 중복 검사
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        });
    }


    /**
     * 전체 회원 조회
     *
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
