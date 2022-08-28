package com.rich.oauth.controller;

import com.rich.oauth.controller.response.MemberResponse;
import com.rich.oauth.domain.Member;
import com.rich.oauth.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public MemberResponse findById(Long id) {
        Member member = memberService.findById(id);
        return MemberResponse.from(member);
    }
}
