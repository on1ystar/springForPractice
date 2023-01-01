package com.example.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.practice.repository.MemberRepository;
import com.example.practice.repository.MemoryMemberRepository;
import com.example.practice.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}