package com.example.practice.service;

import java.util.List;
import java.util.Optional;

import com.example.practice.domain.Member;
import com.example.practice.repository.MemberRepository;
import com.example.practice.repository.MemoryMemberRepository;

public class MemberService {
    
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // DI를 위한 생성자
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    
    
    /**P
     * 회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한 명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
