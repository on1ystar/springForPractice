package com.example.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.practice.domain.Member;
import com.example.practice.repository.MemberRepository;

@SpringBootTest  // 스프링 컨테이너와 테스트를 함께 실행
@Transactional  //  테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setName("hello");
        
        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);

        //Then
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 전체_회원_조회() {

        //Given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring2");
        
        memberService.join(member1);
        memberService.join(member2);
        
        //When
        List<Member> result = memberService.findMembers();

        //Then
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

    @Test
    public void 회원_한명_조회() {

        //Given
        Member member1 = new Member();
        member1.setName("spring1");

        Long memberId = memberService.join(member1);
        
        //When
        Member result = memberService.findOne(memberId).get();

        //Then
        Assertions.assertThat(result.getName()).isEqualTo(member1.getName());
    }
}