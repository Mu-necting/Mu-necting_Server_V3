//package com.munecting.member.service;
//
//import com.munecting.member.dto.MemberDto;
//import com.munecting.member.entity.Member;
//import com.munecting.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class MemberService {
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public void register(MemberDto memberDto){
//
//        String username = memberDto.getUsername();
//        Boolean b = memberRepository.existsByUsername(username);
//
//        if(b)
//            return ;
//
//        String encode = bCryptPasswordEncoder.encode(memberDto.getPassword());
//        memberDto.setPassword(encode);
//
//        Member entity = memberDto.toEntity();
//        entity.setRole("ROLE_USER");
//
//        Member saved = memberRepository.save(entity);
//        log.info(memberDto.toString());
//        log.info(saved.toString());
//    }
//}
