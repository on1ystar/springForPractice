package com.example.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.practice.domain.Member;
import com.example.practice.repository.MemoryMemberRepository;

public class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setName("hello");
        
        //When
        Long saveId = memberService.join(member);

        //Then
        Assertions.assertThat(memberRepository.findById(saveId).get()).isEqualTo(member);

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
        Assertions.assertThat(result).isEqualTo(member1);
    }
}