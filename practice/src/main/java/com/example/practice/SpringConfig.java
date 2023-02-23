package com.example.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.practice.repository.JdbcTemplateMemberRepository;
import com.example.practice.repository.JpaMemberRepository;
import com.example.practice.repository.MemberRepository;
import com.example.practice.service.MemberService;

import jakarta.persistence.EntityManager;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;  // 추가

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}