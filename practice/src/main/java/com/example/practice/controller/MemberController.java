package com.example.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practice.domain.Member;
import com.example.practice.service.MemberService;

@Controller
public class MemberController {
    
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String join(MemberForm form){
        
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){

        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        
        return "/members/list";
    }
}