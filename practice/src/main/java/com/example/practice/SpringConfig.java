package com.example.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.practice.repository.MemberRepository;
import com.example.practice.service.MemberService;

@Configuration
public class SpringConfig {

    // private final DataSource dataSource;
    // private final EntityManager em;  // 추가

    // public SpringConfig(DataSource dataSource, EntityManager em) {
    //     this.dataSource = dataSource;
    //     this.em = em;
    // }
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // @Bean
    // MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
    //     return new JpaMemberRepository(em);
    // }
}