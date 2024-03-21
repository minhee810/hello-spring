package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest{

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // method 끝나면 실행해야함.  순서에 의존하지 않게 스토어를 비워줌.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {

        Member member = new Member();

        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

//        System.out.println("(result == member) = " + (result == member)); // 매번 찍어볼 수 없음.


//        Assertions.assertEquals(member, result); // 기댓값 , 결과값 -> 주피터

        assertThat(member).isEqualTo(result); // 좀 더 명시적임  option + enter -> static import

    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1"); // 이름 저장

        repository.save(member1); // 디비에 저장

        Member member2 = new Member();

        member2.setName("spring2"); // 이름 저장

        repository.save(member2);  // 디비에 저장


        Member result = repository.findByName("spring1").get();  // 이름을 스트링으로 찾음.

        assertThat(result).isEqualTo(member1);  // 찾은 결과와 저장한 값과 같은지 비교하는 테스트

        Member result2 = repository.findByName("spring2").get();

        assertThat(result2).isEqualTo(member2);


    }

    @Test
    public void findAll() {

        Member member1 = new Member();

        member1.setName("spring1");

        repository.save(member1);

        Member member2 = new Member();

        member2.setName("spring2");

        repository.save(member2);

        List<Member> all = repository.findAll();

        assertThat(all.size()).isEqualTo(2);

    }

    // test 는 순서가 보장이 안됨.



}