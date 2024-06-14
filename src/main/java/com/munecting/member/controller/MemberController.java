package com.munecting.member.controller;

//
//import com.munecting.member.dto.MemberDto;
//import com.munecting.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    @GetMapping("/api/success")
    public String ind() {
        return "success";
    }
    @GetMapping("/")
    public String inde() {
        return "root";
    }
}
